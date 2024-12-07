package com.adaptors_batch_service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 1403041089L;

    public static final QReview review = new QReview("review");

    public final com.adaptors_batch_service.common.entity.QBaseEntity _super = new com.adaptors_batch_service.common.entity.QBaseEntity(this);

    public final StringPath comment = createString("comment");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath menteeUuid = createString("menteeUuid");

    public final StringPath mentoringSessionUuid = createString("mentoringSessionUuid");

    public final StringPath mentoringUuid = createString("mentoringUuid");

    public final StringPath mentorUuid = createString("mentorUuid");

    public final StringPath reviewCode = createString("reviewCode");

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
    }

}

