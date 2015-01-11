package se.spline.api.folder;

public class MoveFolderStartedEvent {
	private final FolderId folderId;
	private final FolderId newParentId;

	public MoveFolderStartedEvent(FolderId folderId, FolderId newParentId) {
		this.folderId = folderId;
		this.newParentId = newParentId;
	}

	public FolderId getFolderId() {
		return folderId;
	}

	public FolderId getNewParentId() {
		return newParentId;
	}
}
