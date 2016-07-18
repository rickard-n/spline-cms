package se.spline.query.neo4j.document;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.document.DocumentCreatedEvent;

@Component
public class DocumentListener {
    private final DocumentQueryRepository repository;

    @Autowired
    public DocumentListener(DocumentQueryRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void handle(DocumentCreatedEvent event) {
        repository.save(DocumentEntity.builder()
            .documentId(event.getId().getIdentifier())
            .name(event.getName())
            .build());
    }
}
