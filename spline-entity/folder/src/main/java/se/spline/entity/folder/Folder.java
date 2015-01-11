package se.spline.entity.folder;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import se.spline.api.folder.FolderCreatedEvent;
import se.spline.api.folder.FolderId;
import se.spline.api.folder.FolderMovedEvent;
import se.spline.api.folder.InvalidParentFolderEvent;
import se.spline.api.folder.ValidParentFolderEvent;

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

	public void move(FolderId newParent) {
		apply(new FolderMovedEvent(id, newParent));
	}

	@EventHandler
	public void handle(FolderMovedEvent event) {}

	public void checkValidParent(FolderId childId) {
		if(id.equals(childId)) {
			apply(new InvalidParentFolderEvent(id, childId));
		} else {
			apply(new ValidParentFolderEvent(id, childId));
		}
	}
}
