package com.adaptors_batch_service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMentoringEntity is a Querydsl query type for MentoringEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMentoringEntity extends EntityPathBase<MentoringEntity> {

    private static final long serialVersionUID = 372814843L;

    public static final QMentoringEntity mentoringEntity = new QMentoringEntity("mentoringEntity");

    public final com.adaptors_batch_service.common.entity.QBaseEntity _super = new com.adaptors_batch_service.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final StringPath detail = createString("detail");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final BooleanPath isReusable = createBoolean("isReusable");

    public final StringPath mentoringUuid = createString("mentoringUuid");

    public final StringPath mentorUuid = createString("mentorUuid");

    public final StringPath name = createString("name");

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMentoringEntity(String variable) {
        super(MentoringEntity.class, forVariable(variable));
    }

    public QMentoringEntity(Path<? extends MentoringEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMentoringEntity(PathMetadata metadata) {
        super(MentoringEntity.class, metadata);
    }

}

