package com.adaptors_batch_service.mentorOverview.dto.in;

import lombok.Data;

@Data
public class MentorOverviewDto {
    private String mentorUuid;
    private String nickName;
    private String profileImageUrl;

    private Double reviewScore;
    private Long totalReviewCount;
    private Double reviewStarAvg;
    private Double likeScore;
    private Long totalLikeCount;
    private Double saleScore;
    private Long totalSaleCount;
    private Double totalScore;
}