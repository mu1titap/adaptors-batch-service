package com.adaptors_batch_service.mentorOverview.dto.out;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
public class MentorOverviewResponseDto {
    private String nickName;
    private String profileImageUrl;

    private Long totalReviewCount;
    private Double reviewStarAvg;
    private Long totalLikeCount;
    private Long totalSaleCount;

    @QueryProjection
    public MentorOverviewResponseDto(String nickName, String profileImageUrl, Long totalReviewCount, Double reviewStarAvg, Long totalLikeCount, Long totalSaleCount) {
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.totalReviewCount = totalReviewCount;
        this.reviewStarAvg = reviewStarAvg;
        this.totalLikeCount = totalLikeCount;
        this.totalSaleCount = totalSaleCount;
    }
}