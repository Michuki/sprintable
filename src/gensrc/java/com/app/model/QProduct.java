package com.app.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1314341584L;

    public static final QProduct product = new QProduct("product");

    public final QPersistableEntity _super = new QPersistableEntity(this);

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath image = createString("image");

    public final StringPath name = createString("name");

    public final EnumPath<Product.ProductType> productType = createEnum("productType", Product.ProductType.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata<?> metadata) {
        super(Product.class, metadata);
    }

}

