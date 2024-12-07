package com.adaptors_batch_service.mentorOverview.application;

import com.adaptors_batch_service.mentorOverview.dto.out.MentorOverviewResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MentorOverviewServiceImpl implements  MentorOverviewService{
    @Override
    public MentorOverviewResponseDto findByMentorUuid(String mentorUuid) {
        return null;
    }
}
