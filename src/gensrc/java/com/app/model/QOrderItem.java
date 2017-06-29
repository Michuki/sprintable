package com.app.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderItem is a Querydsl query type for OrderItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderItem extends EntityPathBase<OrderItem> {

    private static final long serialVersionUID = 1021813026L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderItem orderItem = new QOrderItem("orderItem");

    public final QPersistableEntity _super = new QPersistableEntity(this);

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QProduct product;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final NumberPath<java.math.BigDecimal> totalCost = createNumber("totalCost", java.math.BigDecimal.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QOrderItem(String variable) {
        this(OrderItem.class, forVariable(variable), INITS);
    }

    public QOrderItem(Path<? extends OrderItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderItem(PathMetadata<?> metadata, PathInits inits) {
        this(OrderItem.class, metadata, inits);
    }

    public QOrderItem(Class<? extends OrderItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
    }

}

