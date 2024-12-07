package com.adaptors_batch_service.mentorOverview.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.adaptors_batch_service.mentorOverview.dto.out.QBestMentorResponseDto is a Querydsl Projection type for BestMentorResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBestMentorResponseDto extends ConstructorExpression<BestMentorResponseDto> {

    private static final long serialVersionUID = 1485495320L;

    public QBestMentorResponseDto(com.querydsl.core.types.Expression<String> mentorUuid, com.querydsl.core.types.Expression<String> nickName, com.querydsl.core.types.Expression<String> profileImageUrl, com.querydsl.core.types.Expression<Long> totalReviewCount, com.querydsl.core.types.Expression<Double> reviewStarAvg, com.querydsl.core.types.Expression<Long> totalLikeCount, com.querydsl.core.types.Expression<Long> totalSaleCount) {
        super(BestMentorResponseDto.class, new Class<?>[]{String.class, String.class, String.class, long.class, double.class, long.class, long.class}, mentorUuid, nickName, profileImageUrl, totalReviewCount, reviewStarAvg, totalLikeCount, totalSaleCount);
    }

}

