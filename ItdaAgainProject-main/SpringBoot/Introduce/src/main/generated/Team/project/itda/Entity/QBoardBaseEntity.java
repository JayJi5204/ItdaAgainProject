package Team.project.itda.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardBaseEntity is a Querydsl query type for BoardBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBoardBaseEntity extends EntityPathBase<BoardBaseEntity> {

    private static final long serialVersionUID = 1922002703L;

    public static final QBoardBaseEntity boardBaseEntity = new QBoardBaseEntity("boardBaseEntity");

    public final DateTimePath<java.time.LocalDateTime> createDt = createDateTime("createDt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updateDt = createDateTime("updateDt", java.time.LocalDateTime.class);

    public QBoardBaseEntity(String variable) {
        super(BoardBaseEntity.class, forVariable(variable));
    }

    public QBoardBaseEntity(Path<? extends BoardBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardBaseEntity(PathMetadata metadata) {
        super(BoardBaseEntity.class, metadata);
    }

}

