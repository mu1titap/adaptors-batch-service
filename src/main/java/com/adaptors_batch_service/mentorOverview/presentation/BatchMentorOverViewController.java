package com.adaptors_batch_service.mentorOverview.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/batch/mentor-overview")
@Tag(name = "배치", description = "(조회용 X)")
public class BatchMentorOverViewController {

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    public BatchMentorOverViewController(JobLauncher jobLauncher, JobRegistry jobRegistry) {
        this.jobLauncher = jobLauncher;
        this.jobRegistry = jobRegistry;
    }


    @PostMapping("/jdbc/mentor")
    public String jdbcReviewApi(@RequestParam("value") String value) throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("date", value)
                .toJobParameters();

        jobLauncher.run(jobRegistry.getJob("mentorOverviewJob"), jobParameters);

        return "ok";
    }

}