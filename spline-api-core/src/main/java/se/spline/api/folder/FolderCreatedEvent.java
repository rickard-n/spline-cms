package se.spline.api.folder;

public class FolderCreatedEvent {
	private final FolderId id;
	private final String name;
	private final FolderId parentId;

	public FolderCreatedEvent(FolderId id, String name, FolderId parentId) {
		this.id = id;
		this.name = name;

		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public FolderId getParentId() {
		return parentId;
	}

	public FolderId getFolderIdentifier() {
		return id;
	}
}
