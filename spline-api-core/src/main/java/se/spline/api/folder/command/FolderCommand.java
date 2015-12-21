package se.spline.api.folder.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import se.spline.api.folder.FolderId;

public abstract class FolderCommand {
	@TargetAggregateIdentifier
	private final FolderId folderId;


	protected FolderCommand(FolderId folderId) {
		this.folderId = folderId;
	}

	public FolderId getFolderId() {
		return folderId;
	}
}
