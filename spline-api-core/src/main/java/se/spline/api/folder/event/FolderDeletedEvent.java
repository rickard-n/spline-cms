package se.spline.api.folder.event;

import se.spline.api.folder.FolderId;

public class FolderDeletedEvent {
	private final FolderId folderId;

	public FolderDeletedEvent(FolderId folderId) {

		this.folderId = folderId;
	}

	public FolderId getFolderId() {
		return folderId;
	}
}
