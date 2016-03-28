package se.spline.entity.folder;

import org.axonframework.domain.DomainEventMessage;
import org.axonframework.eventsourcing.EventSourcedEntity;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import se.spline.api.folder.FolderId;
import se.spline.api.folder.event.FolderCreatedEvent;
import se.spline.api.folder.event.FolderDeletedEvent;
import se.spline.api.folder.event.ParametersAddedToFolderEvent;
import se.spline.api.folder.event.ParametersRemovedFromFolderEvent;
import se.spline.api.folder.parameter.FolderParameter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Folder extends AbstractAnnotatedAggregateRoot<FolderId> {

	@AggregateIdentifier
	protected FolderId id;

	public Folder(FolderId id, String name, FolderId parentId) {
		apply(new FolderCreatedEvent(id, name, parentId));
	}

	@SuppressWarnings("UnusedDeclaration")
	protected Folder() {
	}

	@Override
	public FolderId getIdentifier() {
		return id;
	}

	@Override
	protected Collection<EventSourcedEntity> getChildEntities() {
		return Collections.emptyList();
	}

	@Override
	protected void handle(DomainEventMessage eventMessage) {
		if (eventMessage.getPayloadType().equals(FolderCreatedEvent.class)) {
			FolderCreatedEvent event = (FolderCreatedEvent) eventMessage.getPayload();
			this.id = event.getId();
		}
	}

	void addParameters(List<FolderParameter<?>> parameters) {
		apply(new ParametersAddedToFolderEvent(id, parameters));
	}

	void delete() {
		apply(new FolderDeletedEvent(id));
	}

	void removeParameters(List<FolderParameter<?>> parameters) {
		apply(new ParametersRemovedFromFolderEvent(id, parameters));
	}
}
