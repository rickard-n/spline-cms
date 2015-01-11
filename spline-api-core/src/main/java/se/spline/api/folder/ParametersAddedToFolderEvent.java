package se.spline.api.folder;

import se.spline.api.folder.parameter.FolderParameter;

import java.util.List;

public class ParametersAddedToFolderEvent {
	private final FolderId id;
	private final List<FolderParameter<?>> parameters;

	public ParametersAddedToFolderEvent(FolderId id, List<FolderParameter<?>> parameters) {
		this.id = id;
		this.parameters = parameters;
	}

	public FolderId getId() {
		return id;
	}

	public List<FolderParameter<?>> getParameters() {
		return parameters;
	}
}
