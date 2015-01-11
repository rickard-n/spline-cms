package se.spline.api.folder;

public class CheckValidParentForFolderCommand extends FolderCommand {
	public static final String COMMAND = "checkValidParentForFolderCommand";
	private final FolderId childId;

	public CheckValidParentForFolderCommand(FolderId id, FolderId childId) {
		super(id);
		this.childId = childId;
	}

	public FolderId getChildId() {
		return childId;
	}
}
