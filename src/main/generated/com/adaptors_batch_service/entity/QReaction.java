package com.adaptors_batch_service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReaction is a Querydsl query type for Reaction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReaction extends EntityPathBase<Reaction> {

    private static final long serialVersionUID = -903560750L;

    public static final QReaction reaction = new QReaction("reaction");

    public final com.adaptors_batch_service.common.entity.QBaseEntity _super = new com.adaptors_batch_service.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath liked = createBoolean("liked");

    public final StringPath targetUuid = createString("targetUuid");

    public final BooleanPath type = createBoolean("type");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath uuid = createString("uuid");

    public QReaction(String variable) {
        super(Reaction.class, forVariable(variable));
    }

    public QReaction(Path<? extends Reaction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReaction(PathMetadata metadata) {
        super(Reaction.class, metadata);
    }

}

