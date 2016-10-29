package se.spline.query.neo4j.type;

import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import se.spline.api.type.TypeId;
import se.spline.domain.Type;
import se.spline.query.neo4j.Neo4jKatharsisQueryRepository;
import se.spline.query.type.TypeQueryRepository;

import java.util.Collections;

@Repository
public class TypeQueryRepositoryImpl extends Neo4jKatharsisQueryRepository<TypeEntity, Type> implements TypeQueryRepository {
    private final Session session;
    private final Neo4jOperations template;

    @Autowired
    public TypeQueryRepositoryImpl(Session session, Neo4jOperations template) {
        super(TypeEntity.class, session, template);
        this.session = session;
        this.template = template;
    }

    @Override
    protected TypeEntity convertResource(Type resource) {
        return TypeEntity.builder()
            .typeId(resource.getId().getIdentifier())
            .baseType(resource.getBaseType())
            .name(resource.getName())
            .properties(Collections.emptyList())
            .build();
    }

    @Override
    protected Type convertEntity(TypeEntity typeEntity) {
        return Type.builder()
            .id(TypeId.from(typeEntity.getTypeId()))
            .name(typeEntity.getName())
            //.displayName(typeEntity.getDisplayName())
            //.description(typeEntity.getDescription())
            .baseType(typeEntity.getBaseType())
            .parent(TypeId.from(typeEntity.getTypeId()))
            //.creatable(typeEntity.isCreatable())
            //.fulltextIndexed(typeEntity.isFulltextIndexed())
            //.properties()
            .build();
    }

    @Override
    public Type findByTypeId(String typeId) {
        return null;
    }

    @Override
    public Iterable<Type> findAllByTypeId(Iterable<String> typeId) {
        return null;
    }
}
