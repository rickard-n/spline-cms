package se.spline.api.folder;

public class FolderDeletedEvent {
	private final FolderId folderId;

	public FolderDeletedEvent(FolderId folderId) {

		this.folderId = folderId;
	}

	public FolderId getFolderId() {
		return folderId;
	}
}
