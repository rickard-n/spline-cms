package se.spline.api.folder;

public abstract class FolderCommand {

	private final FolderId folderId;


	protected FolderCommand(FolderId folderId) {
		this.folderId = folderId;
	}

	public FolderId getFolderId() {
		return folderId;
	}
}
