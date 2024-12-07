package com.adaptors_batch_service.mentorOverview.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.adaptors_batch_service.mentorOverview.dto.out.QMentorOverviewResponseDto is a Querydsl Projection type for MentorOverviewResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMentorOverviewResponseDto extends ConstructorExpression<MentorOverviewResponseDto> {

    private static final long serialVersionUID = 1989888995L;

    public QMentorOverviewResponseDto(com.querydsl.core.types.Expression<String> nickName, com.querydsl.core.types.Expression<String> profileImageUrl, com.querydsl.core.types.Expression<Long> totalReviewCount, com.querydsl.core.types.Expression<Double> reviewStarAvg, com.querydsl.core.types.Expression<Long> totalLikeCount, com.querydsl.core.types.Expression<Long> totalSaleCount) {
        super(MentorOverviewResponseDto.class, new Class<?>[]{String.class, String.class, long.class, double.class, long.class, long.class}, nickName, profileImageUrl, totalReviewCount, reviewStarAvg, totalLikeCount, totalSaleCount);
    }

}

