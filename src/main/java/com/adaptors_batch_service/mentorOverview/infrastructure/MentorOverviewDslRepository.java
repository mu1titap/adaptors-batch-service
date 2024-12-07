package com.adaptors_batch_service.mentorOverview.infrastructure;

import com.adaptors_batch_service.mentorOverview.dto.out.BestMentorResponseDto;
import com.adaptors_batch_service.mentorOverview.dto.out.MentorOverviewResponseDto;
import com.adaptors_batch_service.mentorOverview.dto.out.QBestMentorResponseDto;
import com.adaptors_batch_service.mentorOverview.dto.out.QMentorOverviewResponseDto;
import com.adaptors_batch_service.mentorOverview.entity.QMentorOverView;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.adaptors_batch_service.mentorOverview.entity.QMentorOverView.mentorOverView;

@Repository
@RequiredArgsConstructor
public class MentorOverviewDslRepository {
    private final JPAQueryFactory queryFactory;

    public MentorOverviewResponseDto getMentorOverview(String mentorUuid) {
        return queryFactory.select(new QMentorOverviewResponseDto(mentorOverView.nickName, mentorOverView.profileImageUrl,
                        mentorOverView.totalReviewCount, mentorOverView.reviewStarAvg,
                mentorOverView.totalLikeCount, mentorOverView.totalSaleCount))
                .from(mentorOverView)
                .where(mentorOverView.mentorUuid.eq(mentorUuid))
                .fetchOne();
    }

    public List<BestMentorResponseDto> getBestMentorList(Integer limit){
        return queryFactory.select(new QBestMentorResponseDto(mentorOverView.mentorUuid, mentorOverView.nickName,
                        mentorOverView.profileImageUrl, mentorOverView.totalReviewCount, mentorOverView.reviewStarAvg,
                        mentorOverView.totalLikeCount, mentorOverView.totalSaleCount))
                .from(mentorOverView)
                .orderBy(mentorOverView.totalSaleCount.desc())
                .limit(limit)
                .fetch();
//        return null;
    }


}
