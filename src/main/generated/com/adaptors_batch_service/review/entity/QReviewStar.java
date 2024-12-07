package com.adaptors_batch_service.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewStar is a Querydsl query type for ReviewStar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewStar extends EntityPathBase<ReviewStar> {

    private static final long serialVersionUID = -856471647L;

    public static final QReviewStar reviewStar = new QReviewStar("reviewStar");

    public final com.adaptors_batch_service.common.entity.QBaseEntity _super = new com.adaptors_batch_service.common.entity.QBaseEntity(this);

    public final NumberPath<Double> averageStar = createNumber("averageStar", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mentoringUuid = createString("mentoringUuid");

    public final StringPath mentorUuid = createString("mentorUuid");

    public final NumberPath<Long> reviewCount = createNumber("reviewCount", Long.class);

    public final NumberPath<Long> totalStar = createNumber("totalStar", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QReviewStar(String variable) {
        super(ReviewStar.class, forVariable(variable));
    }

    public QReviewStar(Path<? extends ReviewStar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewStar(PathMetadata metadata) {
        super(ReviewStar.class, metadata);
    }

}

