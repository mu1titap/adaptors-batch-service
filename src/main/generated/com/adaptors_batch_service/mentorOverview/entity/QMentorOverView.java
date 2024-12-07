package com.adaptors_batch_service.mentorOverview.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMentorOverView is a Querydsl query type for MentorOverView
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMentorOverView extends EntityPathBase<MentorOverView> {

    private static final long serialVersionUID = 296875091L;

    public static final QMentorOverView mentorOverView = new QMentorOverView("mentorOverView");

    public final com.adaptors_batch_service.common.entity.QBaseEntity _super = new com.adaptors_batch_service.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> likeScore = createNumber("likeScore", Double.class);

    public final StringPath mentorUuid = createString("mentorUuid");

    public final StringPath nickName = createString("nickName");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final NumberPath<Double> reviewScore = createNumber("reviewScore", Double.class);

    public final NumberPath<Double> reviewStarAvg = createNumber("reviewStarAvg", Double.class);

    public final NumberPath<Double> saleScore = createNumber("saleScore", Double.class);

    public final NumberPath<Long> totalLikeCount = createNumber("totalLikeCount", Long.class);

    public final NumberPath<Long> totalReviewCount = createNumber("totalReviewCount", Long.class);

    public final NumberPath<Long> totalSaleCount = createNumber("totalSaleCount", Long.class);

    public final NumberPath<Double> totalScore = createNumber("totalScore", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMentorOverView(String variable) {
        super(MentorOverView.class, forVariable(variable));
    }

    public QMentorOverView(Path<? extends MentorOverView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMentorOverView(PathMetadata metadata) {
        super(MentorOverView.class, metadata);
    }

}

