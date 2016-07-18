package se.spline.query.neo4j.type;

import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import se.spline.query.neo4j.Neo4jKatharsisQueryRepository;

@Repository
public class TypeQueryRepositoryImpl extends Neo4jKatharsisQueryRepository<TypeEntity> implements TypeQueryRepository {
    private final Session session;
    private final Neo4jOperations template;

    @Autowired
    public TypeQueryRepositoryImpl(Session session, Neo4jOperations template) {
        super(TypeEntity.class, session, template);
        this.session = session;
        this.template = template;
    }

    @Override
    public TypeEntity findByDocumentId(String typeId) {
        return template.loadByProperty(TypeEntity.class, "typeId", typeId);
    }

    @Override
    public Iterable<TypeEntity> findAllByDocumentId(Iterable<String> typeId) {
        return template.loadAllByProperty(TypeEntity.class, "typeId", typeId);
    }
}
