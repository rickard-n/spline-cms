package se.spline.query.neo4j.document;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.NoRepositoryBean;
import se.spline.query.KatharsisQueryRepository;

@NoRepositoryBean
public interface DocumentQueryRepository extends GraphRepository<DocumentEntity>, KatharsisQueryRepository<DocumentEntity> {
    DocumentEntity findByDocumentId(String documentId);
    Iterable<DocumentEntity> findAllByDocumentId(Iterable<String> documentId);
}
