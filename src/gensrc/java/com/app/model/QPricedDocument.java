package com.app.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPricedDocument is a Querydsl query type for PricedDocument
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPricedDocument extends EntityPathBase<PricedDocument> {

    private static final long serialVersionUID = 1478270325L;

    public static final QPricedDocument pricedDocument = new QPricedDocument("pricedDocument");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QPricedDocument(String variable) {
        super(PricedDocument.class, forVariable(variable));
    }

    public QPricedDocument(Path<? extends PricedDocument> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPricedDocument(PathMetadata<?> metadata) {
        super(PricedDocument.class, metadata);
    }

}

