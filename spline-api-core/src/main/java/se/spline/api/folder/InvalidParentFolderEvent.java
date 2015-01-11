package se.spline.api.folder;

public class InvalidParentFolderEvent {
	private final FolderId id;
	private final FolderId childId;

	public InvalidParentFolderEvent(FolderId id, FolderId childId) {
		this.id = id;
		this.childId = childId;
	}

	public FolderId getId() {
		return id;
	}

	public FolderId getChildId() {
		return childId;
	}
}
