package com.adaptors_batch_service.mentoringOverview.job;

import com.adaptors_batch_service.mentoringOverview.dto.out.MentoringOverviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class MentoringOverviewBatch {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final DataSource dataSource; // 데이터 소스 주입
    private final JobExecutionListener jobExecutionListener;
    private final KafkaTemplate<String, MentoringOverviewDto> kafkaMentoringOverviewTemplate;
    private static final String TOPIC_NAME = "mentoring-aggregation-topic";

    @Bean
    public Job mentoringOverviewJob() {
        return new JobBuilder("mentoringOverviewJob", jobRepository)
                .listener(jobExecutionListener) // 처리 시간 측정 리스너 추가
                .start(mentoringOverviewStep())
                .build();
    }

    @Bean
    public Step mentoringOverviewStep() {
        return new StepBuilder("mentoringOverviewStep", jobRepository)
                .<MentoringOverviewDto, MentoringOverviewDto>chunk(10, platformTransactionManager)
                .reader(mentoringOverviewReader())
                //.writer(mentoringOverviewWriter())
                .writer(mentoringCompositeItemWriter())
                .build();
    }

    @Bean
    public JdbcPagingItemReader<MentoringOverviewDto> mentoringOverviewReader() {
        return new JdbcPagingItemReaderBuilder<MentoringOverviewDto>()
                .name("mentoringOverviewReader")
                .dataSource(dataSource)
                .selectClause("SELECT mentoring_uuid, review_score, total_review_count, review_star_avg, " +
                        " sale_score, total_sale_count, total_score")
                .fromClause("FROM mentoring_overview_source") // 뷰 사용
                .sortKeys(Map.of("mentoring_uuid", Order.ASCENDING))
                .rowMapper((rs, rowNum) -> {
                    MentoringOverviewDto dto = new MentoringOverviewDto();
                    dto.setMentoringUuid(rs.getString("mentoring_uuid"));
                    dto.setReviewScore(rs.getDouble("review_score"));
                    dto.setTotalReviewCount(rs.getLong("total_review_count"));
                    dto.setReviewStarAvg(rs.getDouble("review_star_avg"));
                    dto.setSaleScore(rs.getDouble("sale_score"));
                    dto.setTotalSaleCount(rs.getLong("total_sale_count"));
                    dto.setTotalScore(rs.getDouble("total_score"));
                    return dto;
                })
                .pageSize(10)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<MentoringOverviewDto> mentoringOverviewWriter() {
        return new JdbcBatchItemWriterBuilder<MentoringOverviewDto>()
                .dataSource(dataSource)
                .sql("INSERT INTO mentoring_overview (mentoring_uuid, review_score, total_review_count, " +
                        "review_star_avg, sale_score, total_sale_count, total_score, created_at, updated_at) " +
                        "VALUES (:mentoringUuid, :reviewScore, :totalReviewCount, :reviewStarAvg, " +
                        " :saleScore, :totalSaleCount, :totalScore, NOW(), NOW()) " +
                        "ON DUPLICATE KEY UPDATE " +
                            "review_score = :reviewScore, " +
                            "total_review_count = :totalReviewCount, " +
                            "review_star_avg = :reviewStarAvg, " +
                            "sale_score = :saleScore, " +
                            "total_sale_count = :totalSaleCount, " +
                            "total_score = :totalScore, " +
                            "updated_at = NOW()")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }

    @Bean
    public ItemWriter<MentoringOverviewDto> mentoringOverviewKafkaWriter() {
        return items -> items.forEach(item -> {
            try {
                kafkaMentoringOverviewTemplate.send(TOPIC_NAME, item);
                log.info("Kafka 멘토링 집계 JOB 이벤트 발행 성공: {}", item);
            } catch (Exception e) {
                log.error("Kafka 멘토링 집계 JOB 이벤트 발행 실패: {}", item, e);
                throw e;
            }
        });
    }

    @Bean
    public CompositeItemWriter<MentoringOverviewDto> mentoringCompositeItemWriter() {
        CompositeItemWriter<MentoringOverviewDto> compositeItemWriter = new CompositeItemWriter<>();
        compositeItemWriter.setDelegates(List.of(mentoringOverviewWriter(), mentoringOverviewKafkaWriter()));
        return compositeItemWriter;
    }

}



