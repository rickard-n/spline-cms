package se.spline.api.folder.command;

import se.spline.api.folder.FolderId;

public abstract class FolderCommand {

	private final FolderId folderId;


	protected FolderCommand(FolderId folderId) {
		this.folderId = folderId;
	}

	public FolderId getFolderId() {
		return folderId;
	}
}
