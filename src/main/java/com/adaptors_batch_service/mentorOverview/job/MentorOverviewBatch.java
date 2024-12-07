package com.adaptors_batch_service.mentorOverview.job;

import com.adaptors_batch_service.mentorOverview.dto.in.MentorOverviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
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
public class MentorOverviewBatch {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final DataSource dataSource; // 데이터 소스 주입
    private final JobExecutionListener jobExecutionListener;

    @Bean
    public Job mentorOverviewJob() {
        return new JobBuilder("mentorOverviewJob", jobRepository)
                .listener(jobExecutionListener) // 처리 시간 측정 리스너 추가
                .start(mentorOverviewStep())
                .build();
    }

    @Bean
    public Step mentorOverviewStep() {
        return new StepBuilder("mentorOverviewStep", jobRepository)
                .<MentorOverviewDto, MentorOverviewDto>chunk(10, platformTransactionManager)
                .reader(mentorOverviewReader())
                .writer(mentorOverviewWriter())
                .build();
    }

    @Bean
    public JdbcPagingItemReader<MentorOverviewDto> mentorOverviewReader() {
        return new JdbcPagingItemReaderBuilder<MentorOverviewDto>()
                .name("mentorOverviewReader")
                .dataSource(dataSource)
                .selectClause("SELECT mentor_uuid, nick_name, profile_image_url, review_score, total_review_count, review_star_avg, " +
                        "like_score, total_like_count, sale_score, total_sale_count, total_score")
                .fromClause("FROM mentor_overview_source") // 뷰 사용
                .sortKeys(Map.of("mentor_uuid", Order.ASCENDING))
                .rowMapper((rs, rowNum) -> {
                    MentorOverviewDto dto = new MentorOverviewDto();
                    dto.setMentorUuid(rs.getString("mentor_uuid"));
                    dto.setNickName(rs.getString("nick_name"));
                    dto.setProfileImageUrl(rs.getString("profile_image_url"));
                    dto.setReviewScore(rs.getDouble("review_score"));
                    dto.setTotalReviewCount(rs.getLong("total_review_count"));
                    dto.setReviewStarAvg(rs.getDouble("review_star_avg"));
                    dto.setLikeScore(rs.getDouble("like_score"));
                    dto.setTotalLikeCount(rs.getLong("total_like_count"));
                    dto.setSaleScore(rs.getDouble("sale_score"));
                    dto.setTotalSaleCount(rs.getLong("total_sale_count"));
                    dto.setTotalScore(rs.getDouble("total_score"));
                    return dto;
                })
                .pageSize(10)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<MentorOverviewDto> mentorOverviewWriter() {
        return new JdbcBatchItemWriterBuilder<MentorOverviewDto>()
                .dataSource(dataSource)
                .sql("INSERT INTO mentor_overview (mentor_uuid, nick_name, profile_image_url, review_score, total_review_count, " +
                        "review_star_avg, like_score, total_like_count, sale_score, total_sale_count, total_score, created_at, updated_at) " +
                        "VALUES (:mentorUuid, :nickName, :profileImageUrl, :reviewScore, :totalReviewCount, :reviewStarAvg, " +
                        ":likeScore, :totalLikeCount, :saleScore, :totalSaleCount, :totalScore, NOW(), NOW()) " +
                        "ON DUPLICATE KEY UPDATE " +
                            "nick_name = :nickName, " +
                            "profile_image_url = :profileImageUrl, " +
                            "review_score = :reviewScore, " +
                            "total_review_count = :totalReviewCount, " +
                            "review_star_avg = :reviewStarAvg, " +
                            "like_score = :likeScore, " +
                            "total_like_count = :totalLikeCount, " +
                            "sale_score = :saleScore, " +
                            "total_sale_count = :totalSaleCount, " +
                            "total_score = :totalScore, " +
                            "updated_at = NOW()")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}



