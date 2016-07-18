package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.ResourceRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Component;
import se.spline.api.document.DocumentId;
import se.spline.api.document.command.CreateDocumentCommand;
import se.spline.api.model.Document;
import se.spline.api.repository.builder.DocumentFactory;
import se.spline.query.neo4j.document.DocumentQueryRepository;

@Component
public class DocumentResourceRepository implements ResourceRepository<Document, String> {
    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private DocumentQueryRepository documentQueryRepository;

    @Autowired
    private Neo4jOperations template;

    @Override
    public Document findOne(String id, QueryParams queryParams) {
        return DocumentFactory.from(documentQueryRepository.findByDocumentId(id));
    }

    @Override
    public Iterable<Document> findAll(QueryParams queryParams) {
        return DocumentFactory.from(documentQueryRepository.findAll());
    }

    @Override
    public Iterable<Document> findAll(Iterable<String> ids, QueryParams queryParams) {
        return DocumentFactory.from(documentQueryRepository.findAllByDocumentId(ids));
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public  Document save(Document entity) {
        return findOne(((DocumentId)commandGateway.sendAndWait(new CreateDocumentCommand(new DocumentId(), entity.getName()))).getIdentifier(), null);
    }
}
