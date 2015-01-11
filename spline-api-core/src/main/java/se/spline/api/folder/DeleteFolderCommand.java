package se.spline.api.folder;

public class DeleteFolderCommand extends FolderCommand {
	public final static String COMMAND = "deleteFolderCommand";

	public DeleteFolderCommand(FolderId folderId) {
		super(folderId);
	}
}
