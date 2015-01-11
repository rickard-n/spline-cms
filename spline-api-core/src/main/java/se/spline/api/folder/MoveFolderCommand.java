package se.spline.api.folder;

public class MoveFolderCommand extends FolderCommand {
	public static final String COMMAND = "moveFolderCommand";

	private final FolderId newParentId;

	public MoveFolderCommand(FolderId folderId, FolderId newParentId) {
		super(folderId);
		this.newParentId = newParentId;
	}

	public FolderId getNewParentId() {
		return newParentId;
	}
}
