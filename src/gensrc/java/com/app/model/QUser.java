package com.app.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1233180278L;

    public static final QUser user = new QUser("user");

    public final QPersistableEntity _super = new QPersistableEntity(this);

    public final StringPath address = createString("address");

    public final StringPath city = createString("city");

    public final StringPath createdDate = createString("createdDate");

    public final StringPath dateOfBirth = createString("dateOfBirth");

    public final StringPath firstName = createString("firstName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath lastName = createString("lastName");

    public final StringPath location = createString("location");

    public final StringPath mobileNumber = createString("mobileNumber");

    public final StringPath modifiedDate = createString("modifiedDate");

    public final StringPath password = createString("password");

    public final StringPath userName = createString("userName");

    public final EnumPath<User.UserType> userType = createEnum("userType", User.UserType.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final StringPath zipCode = createString("zipCode");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata<?> metadata) {
        super(User.class, metadata);
    }

}

