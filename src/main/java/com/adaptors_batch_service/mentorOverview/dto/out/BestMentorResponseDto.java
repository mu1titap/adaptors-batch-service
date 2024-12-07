package com.adaptors_batch_service.mentorOverview.dto.out;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class BestMentorResponseDto {
    private String mentorUuid;
    private String nickName;
    private String profileImageUrl;

    private Long totalReviewCount;
    private Double reviewStarAvg;
    private Long totalLikeCount;
    private Long totalSaleCount;

    @QueryProjection
    public BestMentorResponseDto(String mentorUuid, String nickName, String profileImageUrl, Long totalReviewCount, Double reviewStarAvg, Long totalLikeCount, Long totalSaleCount) {
        this.mentorUuid = mentorUuid;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.totalReviewCount = totalReviewCount;
        this.reviewStarAvg = reviewStarAvg;
        this.totalLikeCount = totalLikeCount;
        this.totalSaleCount = totalSaleCount;
    }
}