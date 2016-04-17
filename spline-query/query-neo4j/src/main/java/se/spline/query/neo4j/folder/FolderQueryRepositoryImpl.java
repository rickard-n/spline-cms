package se.spline.query.neo4j.folder;

import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import se.spline.query.neo4j.Neo4jKatharsisQueryRepository;

import java.util.Collections;

@Repository
public class FolderQueryRepositoryImpl extends Neo4jKatharsisQueryRepository<FolderEntity> implements FolderQueryRepository {

    private final Session session;
    private final Neo4jOperations template;

    @Autowired
    public FolderQueryRepositoryImpl(Session session, Neo4jOperations template) {
        super(FolderEntity.class, session);
        this.session = session;
        this.template = template;
    }

    @Override
    public FolderEntity findByFolderId(String folderId) {
        return template.loadByProperty(FolderEntity.class, "folderId", folderId);
    }

    @Override
    public Iterable<FolderEntity> findAllByFolderId(Iterable<String> folderId) {
        return template.loadAllByProperty(FolderEntity.class, "folderId", folderId);
    }

    @Override

    public Iterable<FolderEntity> findChildrenForFolderWithId(String folderId) {
        return session.query(FolderEntity.class, "MATCH (:Folder {folderId:{folderId}})<-[:PUBLISHED_IN]-(c:Folder) return c", Collections.singletonMap("folderId", folderId));
    }

    @Override
    public <S extends FolderEntity> S save(S s, int depth) {
        return super.save(s, depth);
    }

    @Override
    public <S extends FolderEntity> Iterable<S> save(Iterable<S> entities, int depth) {
        return super.save(entities, depth);
    }

    @Override
    public <S extends FolderEntity> S save(S entity) {
        return super.save(entity);
    }

    @Override
    public <S extends FolderEntity> Iterable<S> save(Iterable<S> entities) {
        return super.save(entities);
    }
}
