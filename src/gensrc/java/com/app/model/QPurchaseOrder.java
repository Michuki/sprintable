package com.app.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPurchaseOrder is a Querydsl query type for PurchaseOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPurchaseOrder extends EntityPathBase<PurchaseOrder> {

    private static final long serialVersionUID = 1303204846L;

    public static final QPurchaseOrder purchaseOrder = new QPurchaseOrder("purchaseOrder");

    public final QPersistableEntity _super = new QPersistableEntity(this);

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    public final DatePath<java.time.LocalDate> createdDate = createDate("createdDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<OrderItem, QOrderItem> orderItems = this.<OrderItem, QOrderItem>createList("orderItems", OrderItem.class, QOrderItem.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final EnumPath<Status> status = createEnum("status", Status.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QPurchaseOrder(String variable) {
        super(PurchaseOrder.class, forVariable(variable));
    }

    public QPurchaseOrder(Path<? extends PurchaseOrder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPurchaseOrder(PathMetadata<?> metadata) {
        super(PurchaseOrder.class, metadata);
    }

}

