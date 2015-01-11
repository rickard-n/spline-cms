package se.spline.entity.folder;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.springframework.beans.factory.annotation.Autowired;
import se.spline.api.folder.FolderId;

public abstract class FolderManagerSaga extends AbstractAnnotatedSaga {
	private transient CommandBus commandBus;

	private FolderId folderId;
	private FolderId newParentId;

	public CommandBus getCommandBus() {
		return commandBus;
	}

	@Autowired
	public void setCommandBus(CommandBus commandBus) {
		this.commandBus = commandBus;
	}

	public void setFolderId(FolderId folderId) {
		this.folderId = folderId;
	}

	public FolderId getFolderId() {
		return folderId;
	}

	public FolderId getNewParentId() {
		return newParentId;
	}

	public void setNewParentId(FolderId newParentId) {
		this.newParentId = newParentId;
	}
}
