package se.spline.query.neo4j.document;

import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import se.spline.query.neo4j.Neo4jKatharsisQueryRepository;

@Repository
public class DocumentQueryRepositoryImpl extends Neo4jKatharsisQueryRepository<DocumentEntity> implements DocumentQueryRepository {
    private final Session session;
    private final Neo4jOperations template;

    @Autowired
    public DocumentQueryRepositoryImpl(Session session, Neo4jOperations template) {
        super(DocumentEntity.class, session, template);
        this.session = session;
        this.template = template;
    }

    @Override
    public DocumentEntity findByDocumentId(String documentId) {
        return template.loadByProperty(DocumentEntity.class, "documentId", documentId);
    }

    @Override
    public Iterable<DocumentEntity> findAllByDocumentId(Iterable<String> documentId) {
        return template.loadAllByProperty(DocumentEntity.class, "documentId", documentId);
    }
}
