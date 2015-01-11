package se.spline.entity.folder;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import se.spline.api.folder.FolderCreatedEvent;
import se.spline.api.folder.FolderDeletedEvent;
import se.spline.api.folder.FolderId;
import se.spline.api.folder.ParametersAddedToFolderEvent;
import se.spline.api.folder.ParametersRemovedFromFolderEvent;
import se.spline.api.folder.parameter.FolderParameter;

import java.util.List;

public class Folder extends AbstractAnnotatedAggregateRoot<FolderId> {

	@AggregateIdentifier
	private FolderId id;

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

	@EventHandler
	public void handle(FolderCreatedEvent event) {
		this.id = event.getFolderIdentifier();
	}

	public void addParameters(List<FolderParameter<?>> parameters) {
		apply(new ParametersAddedToFolderEvent(id, parameters));
	}

	public void delete() {
		apply(new FolderDeletedEvent(id));
	}

	public void removeParameters(List<FolderParameter<?>> parameters) {
		apply(new ParametersRemovedFromFolderEvent(id, parameters));
	}
}
