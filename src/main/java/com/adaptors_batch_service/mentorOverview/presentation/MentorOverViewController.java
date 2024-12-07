package com.adaptors_batch_service.mentorOverview.presentation;

import com.adaptors_batch_service.common.entity.BaseResponse;
import com.adaptors_batch_service.mentorOverview.dto.out.BestMentorResponseDto;
import com.adaptors_batch_service.mentorOverview.dto.out.MentorOverviewResponseDto;
import com.adaptors_batch_service.mentorOverview.infrastructure.MentorOverviewDslRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/adaptors-batch-service/mentor-overview")
@Tag(name = "멘토 집계", description = "멘토 집계 데이터 조회")
public class MentorOverViewController {
    private final MentorOverviewDslRepository mentorOverviewDslRepository;

    @GetMapping("/{mentorUuid}")
    @Operation(summary = "멘토 집계 데이터 단건 조회" , description = "멘토 집계 데이터 조회")
    public BaseResponse<MentorOverviewResponseDto> getSessionRequestInfo(@RequestHeader("userUuid") String  userUuid){
        return new BaseResponse<>(
                mentorOverviewDslRepository.getMentorOverview(userUuid)
        );
    }

    @GetMapping("/best-mentor-list")
    @Operation(summary = "베스트 멘토 리스트 조회" , description = "- limit 인자로 필요한 만큼 조회. 총 점수 내림차순으로 출력<br>" +
            " -점수 기준-<br>" +
            " 1. 좋아요 하나당 0.2점 <br>" +
            " 2. 리뷰 1점 = -2 , 2점 = -1, 3점 = 0, 4점 = 1, 5점 = 2 <br>" +
            " 3. 완료된 멘티의 세션 신청 1개당 1점 <br>")
    public BaseResponse<List<BestMentorResponseDto>> getBestMentorList(@ParameterObject @RequestParam("limit") Integer limit){
        return new BaseResponse<>(
                mentorOverviewDslRepository.getBestMentorList(limit)
        );
    }



}