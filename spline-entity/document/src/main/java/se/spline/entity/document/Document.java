package se.spline.entity.document;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.spline.api.document.DocumentCreatedEvent;
import se.spline.api.document.DocumentId;
import se.spline.api.document.command.CreateDocumentCommand;

public class Document extends AbstractAnnotatedAggregateRoot<DocumentId> {
    private final Logger logger = LoggerFactory.getLogger(Document.class);

    @AggregateIdentifier
	private DocumentId identifier;

    private String name;

    private boolean deleted;
    private boolean published;

	@SuppressWarnings("UnusedDeclaration")
	public Document() {}

    @CommandHandler
	public Document(CreateDocumentCommand command) {
        logger.debug("Command: 'CreateDocumentCommand' received.");
        logger.debug("Queuing up a new DocumentCreatedEvent for document '{}'", command.getDocumentId());
        this.identifier = command.getDocumentId();
        apply(new DocumentCreatedEvent(command.getDocumentId(), command.getName()));
    }

	@Override
	public DocumentId getIdentifier() {
		return identifier;
	}

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    public void on(DocumentCreatedEvent event) {
        this.identifier = event.getId();
        this.name = event.getName();
    }

    public boolean isPublished() {
        return published;
    }
}
