package se.spline.api.folder.command;

import se.spline.api.folder.FolderId;
import se.spline.api.folder.parameter.FolderParameter;

import java.util.List;

public class AddParametersToFolderCommand extends FolderCommand {
	public final static String COMMAND = "addParametersToFolderCommand";
	private final List<FolderParameter<?>> parameters;

	public AddParametersToFolderCommand(FolderId id, List<FolderParameter<?>> parameters) {
		super(id);

		this.parameters = parameters;
	}

	public List<FolderParameter<?>> getParameters() {
		return parameters;
	}
}
