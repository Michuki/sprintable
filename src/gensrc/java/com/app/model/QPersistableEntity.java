package com.app.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPersistableEntity is a Querydsl query type for PersistableEntity
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QPersistableEntity extends EntityPathBase<PersistableEntity> {

    private static final long serialVersionUID = 528108370L;

    public static final QPersistableEntity persistableEntity = new QPersistableEntity("persistableEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QPersistableEntity(String variable) {
        super(PersistableEntity.class, forVariable(variable));
    }

    public QPersistableEntity(Path<? extends PersistableEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersistableEntity(PathMetadata<?> metadata) {
        super(PersistableEntity.class, metadata);
    }

}

