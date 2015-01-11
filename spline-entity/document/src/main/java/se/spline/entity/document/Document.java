package se.spline.entity.document;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import se.spline.api.document.DocumentCreatedEvent;
import se.spline.api.document.DocumentId;

public class Document extends AbstractAnnotatedAggregateRoot<DocumentId> {
	@AggregateIdentifier
	private DocumentId id;

	public Document(DocumentId documentId, String name) {
		apply(new DocumentCreatedEvent(documentId, name));
	}

	@SuppressWarnings("UnusedDeclaration")
	protected Document() {}

	@Override
	public DocumentId getIdentifier() {
		return id;
	}

	@EventHandler
	public void handle(DocumentCreatedEvent event) {
		this.id = event.getId();
	}
}
