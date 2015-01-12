package se.spline.api.folder.command;

import se.spline.api.folder.FolderId;

public class DeleteFolderCommand extends FolderCommand {
	public final static String COMMAND = "deleteFolderCommand";

	public DeleteFolderCommand(FolderId folderId) {
		super(folderId);
	}
}
