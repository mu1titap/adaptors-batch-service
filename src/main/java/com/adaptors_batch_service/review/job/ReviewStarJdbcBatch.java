package com.adaptors_batch_service.review.job;

import com.adaptors_batch_service.review.dto.out.ReviewStarDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class ReviewStarJdbcBatch {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final DataSource dataSource; // 데이터 소스 주입
    private final JobExecutionListener jobExecutionListener;

    // 배치 작업 정의
    @Bean
    public Job productReviewStarJdbcJob() {
        return new JobBuilder("productReviewStarJdbcJob", jobRepository)
                .listener(jobExecutionListener) // 처리 시간 측정 리스너 추가
                .start(mentoringReviewStarJdbcStep())
                .build();
    }

    // Step 정의
    @Bean
    public Step mentoringReviewStarJdbcStep() {
        return new StepBuilder("mentoringReviewStarJdbcStep", jobRepository)
                .<ReviewStarDto, ReviewStarDto>chunk(10, platformTransactionManager) // chunk size 설정
                .reader(reviewStarJdbcReader())
                //.processor(reviewStarProcessor())
                .writer(reviewStarJdbcWriter())
                .build();
    }

    @Bean
    public JdbcPagingItemReader<ReviewStarDto> reviewStarJdbcReader() {
        return new JdbcPagingItemReaderBuilder<ReviewStarDto>()
                .name("reviewStarJdbcReader")
                .dataSource(dataSource)
                .selectClause("SELECT mentoring_uuid, COUNT(*) as review_count, SUM(score) as total_star, " +
                        "AVG(score) as average_star , mentor_uuid")
                .fromClause("FROM source_review")
                .whereClause("WHERE is_deleted = false")
                .groupClause("GROUP BY mentoring_uuid , mentor_uuid")
                .sortKeys(Map.of("mentoring_uuid", Order.ASCENDING))
                .rowMapper((rs, rowNum) -> {
                    ReviewStarDto dto = new ReviewStarDto();
                    dto.setMentoringUuid(rs.getString("mentoring_uuid"));
                    dto.setReviewCount(rs.getLong("review_count"));
                    dto.setTotalStar(rs.getLong("total_star"));
                    dto.setAverageStar(rs.getDouble("average_star"));
                    dto.setMentorUuid(rs.getString("mentor_uuid"));
                    return dto;
                })
                .pageSize(10)
                .build();
    }

//    @Bean(name = "jdbcReviewStarProcessor")
//    public ItemProcessor<ReviewStarDto, ReviewStarDto> reviewStarProcessor() {
//        return reviewStarDto -> reviewStarDto; // 딱히 처리 X , 생략해도 무방함
//    }

    @Bean
    public JdbcBatchItemWriter<ReviewStarDto> reviewStarJdbcWriter() {
        return new JdbcBatchItemWriterBuilder<ReviewStarDto>()
                .dataSource(dataSource)
                .sql("INSERT INTO review_star (mentoring_uuid, review_count, average_star, total_star, mentor_uuid, created_at, updated_at) " +
                        "VALUES (:mentoringUuid, :reviewCount, :averageStar,  :totalStar, :mentorUuid ,SYSDATE(), SYSDATE()) " +
                        "ON DUPLICATE KEY UPDATE " +
                        "review_count = :reviewCount, " +
                        "average_star = :averageStar, " +
                        "total_star = :totalStar, " +
                        "mentor_uuid = :mentorUuid, " +
                        "updated_at = SYSDATE()")
                // ReviewStarDto의 필드 값 sql 파라미터에 매핑
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}
