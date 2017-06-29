package com.app.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QShop is a Querydsl query type for Shop
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QShop extends EntityPathBase<Shop> {

    private static final long serialVersionUID = -1233250123L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShop shop = new QShop("shop");

    public final QPersistableEntity _super = new QPersistableEntity(this);

    public final StringPath address = createString("address");

    public final NumberPath<java.math.BigDecimal> cost = createNumber("cost", java.math.BigDecimal.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath location = createString("location");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> seatNumber = createNumber("seatNumber", Integer.class);

    public final QUser user;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QShop(String variable) {
        this(Shop.class, forVariable(variable), INITS);
    }

    public QShop(Path<? extends Shop> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShop(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShop(PathMetadata<?> metadata, PathInits inits) {
        this(Shop.class, metadata, inits);
    }

    public QShop(Class<? extends Shop> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

