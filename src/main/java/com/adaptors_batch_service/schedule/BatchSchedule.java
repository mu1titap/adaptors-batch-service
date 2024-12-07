package com.adaptors_batch_service.schedule;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class BatchSchedule {

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    public BatchSchedule(JobLauncher jobLauncher, JobRegistry jobRegistry) {
        this.jobLauncher = jobLauncher;
        this.jobRegistry = jobRegistry;
    }


    /**
     * 멘토링 별 리뷰 집계 매일 00 시 마다 실행
     */
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void runProductReviewStarJdbcJob() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = dateFormat.format(new Date());

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("date", date)
                .toJobParameters();

        jobLauncher.run(jobRegistry.getJob("productReviewStarJdbcJob"), jobParameters);
    }


}