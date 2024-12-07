package com.adaptors_batch_service.mentorOverview.application;

import com.adaptors_batch_service.mentorOverview.dto.out.MentorOverviewResponseDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MentorOverviewService {
    MentorOverviewResponseDto findByMentorUuid(String mentorUuid);
}
