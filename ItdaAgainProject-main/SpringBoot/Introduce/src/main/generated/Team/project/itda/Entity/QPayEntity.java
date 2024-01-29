package Team.project.itda.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayEntity is a Querydsl query type for PayEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayEntity extends EntityPathBase<PayEntity> {

    private static final long serialVersionUID = -1629441056L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPayEntity payEntity = new QPayEntity("payEntity");

    public final StringPath depositDetails = createString("depositDetails");

    public final NumberPath<Long> depositMoney = createNumber("depositMoney", Long.class);

    public final DateTimePath<java.time.LocalDateTime> depositTime = createDateTime("depositTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> payId = createNumber("payId", Long.class);

    public final NumberPath<Long> totalMoney = createNumber("totalMoney", Long.class);

    public final QUserEntity userEntity;

    public final StringPath withdrawDetails = createString("withdrawDetails");

    public final NumberPath<Long> withdrawMoney = createNumber("withdrawMoney", Long.class);

    public final DateTimePath<java.time.LocalDateTime> withdrawTime = createDateTime("withdrawTime", java.time.LocalDateTime.class);

    public QPayEntity(String variable) {
        this(PayEntity.class, forVariable(variable), INITS);
    }

    public QPayEntity(Path<? extends PayEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPayEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPayEntity(PathMetadata metadata, PathInits inits) {
        this(PayEntity.class, metadata, inits);
    }

    public QPayEntity(Class<? extends PayEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

