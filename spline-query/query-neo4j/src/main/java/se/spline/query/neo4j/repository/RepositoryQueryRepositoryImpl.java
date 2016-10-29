package se.spline.query.neo4j.repository;

import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import se.spline.query.neo4j.archive.Neo4jKatharsisQueryRepository;

@Repository
public class RepositoryQueryRepositoryImpl extends Neo4jKatharsisQueryRepository<RepositoryEntity> implements RepositoryQueryRepository {

    private final Session session;
    private final Neo4jOperations template;

    @Autowired
    public RepositoryQueryRepositoryImpl(Session session, Neo4jOperations template) {
        super(RepositoryEntity.class, session, template);
        this.session = session;
        this.template = template;
    }

    @Override
    public RepositoryEntity findByRepositoryId(String id) {
        return template.loadByProperty(RepositoryEntity.class, "repositoryId", id);
    }

    @Override
    public Iterable<RepositoryEntity> findAllByRepositoryId(Iterable<String> ids) {
        return template.loadAllByProperty(RepositoryEntity.class, "repositoryId", ids);
    }

    @Override
    public void deleteByRepositoryId(String id) {
        super.delete(findByRepositoryId(id));
    }

    @Override
    public RepositoryEntity saveResource(RepositoryEntity resource) {
        return null;
    }
}
