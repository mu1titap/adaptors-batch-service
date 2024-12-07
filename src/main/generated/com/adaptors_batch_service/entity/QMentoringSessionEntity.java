package com.adaptors_batch_service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMentoringSessionEntity is a Querydsl query type for MentoringSessionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMentoringSessionEntity extends EntityPathBase<MentoringSessionEntity> {

    private static final long serialVersionUID = -871895295L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMentoringSessionEntity mentoringSessionEntity = new QMentoringSessionEntity("mentoringSessionEntity");

    public final com.adaptors_batch_service.common.entity.QBaseEntity _super = new com.adaptors_batch_service.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> deadlineDate = createDate("deadlineDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> endTime = createTime("endTime", java.time.LocalTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isClosed = createBoolean("isClosed");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Integer> maxHeadCount = createNumber("maxHeadCount", Integer.class);

    public final QMentoringEntity mentoringEntity;

    public final NumberPath<Integer> minHeadCount = createNumber("minHeadCount", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath uuid = createString("uuid");

    public QMentoringSessionEntity(String variable) {
        this(MentoringSessionEntity.class, forVariable(variable), INITS);
    }

    public QMentoringSessionEntity(Path<? extends MentoringSessionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMentoringSessionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMentoringSessionEntity(PathMetadata metadata, PathInits inits) {
        this(MentoringSessionEntity.class, metadata, inits);
    }

    public QMentoringSessionEntity(Class<? extends MentoringSessionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mentoringEntity = inits.isInitialized("mentoringEntity") ? new QMentoringEntity(forProperty("mentoringEntity")) : null;
    }

}

