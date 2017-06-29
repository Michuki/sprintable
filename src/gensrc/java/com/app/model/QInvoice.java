package com.app.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QInvoice is a Querydsl query type for Invoice
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInvoice extends EntityPathBase<Invoice> {

    private static final long serialVersionUID = -710952690L;

    public static final QInvoice invoice = new QInvoice("invoice");

    public final QPersistableEntity _super = new QPersistableEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath invoiceBody = createString("invoiceBody");

    public final DatePath<java.time.LocalDate> issueDate = createDate("issueDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> paymentDueDate = createDate("paymentDueDate", java.time.LocalDate.class);

    public final ListPath<PricedDocument, QPricedDocument> pricedDocuments = this.<PricedDocument, QPricedDocument>createList("pricedDocuments", PricedDocument.class, QPricedDocument.class, PathInits.DIRECT2);

    public final EnumPath<Invoice.InvoiceStatus> status = createEnum("status", Invoice.InvoiceStatus.class);

    public final NumberPath<java.math.BigDecimal> totalAmount = createNumber("totalAmount", java.math.BigDecimal.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QInvoice(String variable) {
        super(Invoice.class, forVariable(variable));
    }

    public QInvoice(Path<? extends Invoice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInvoice(PathMetadata<?> metadata) {
        super(Invoice.class, metadata);
    }

}

