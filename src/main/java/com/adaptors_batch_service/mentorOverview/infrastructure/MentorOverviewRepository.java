package com.adaptors_batch_service.mentorOverview.infrastructure;

import com.adaptors_batch_service.mentorOverview.entity.MentorOverView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorOverviewRepository extends JpaRepository<MentorOverView,Long> {
}
