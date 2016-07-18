package se.spline.entity.document;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import se.spline.api.document.DocumentCreatedEvent;
import se.spline.api.document.DocumentId;
import se.spline.api.document.command.CreateDocumentCommand;

public class Document extends AbstractAnnotatedAggregateRoot<DocumentId> {
	@AggregateIdentifier
	private DocumentId identifier;

    private boolean deleted;
    private boolean published;

	@SuppressWarnings("UnusedDeclaration")
	public Document() {}

    @CommandHandler
	public Document(CreateDocumentCommand command) {
        identifier = command.getDocumentId();
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

    public boolean isPublished() {
        return published;
    }
}
