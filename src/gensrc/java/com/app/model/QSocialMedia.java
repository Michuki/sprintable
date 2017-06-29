package com.app.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSocialMedia is a Querydsl query type for SocialMedia
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSocialMedia extends EntityPathBase<SocialMedia> {

    private static final long serialVersionUID = -641424936L;

    public static final QSocialMedia socialMedia = new QSocialMedia("socialMedia");

    public final QPersistableEntity _super = new QPersistableEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSocialMedia(String variable) {
        super(SocialMedia.class, forVariable(variable));
    }

    public QSocialMedia(Path<? extends SocialMedia> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSocialMedia(PathMetadata<?> metadata) {
        super(SocialMedia.class, metadata);
    }

}

