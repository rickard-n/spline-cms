package se.spline.api.folder;

import se.spline.api.folder.parameter.FolderParameter;

import java.util.List;

public class ParametersRemovedFromFolderEvent {
	private final FolderId folderId;
	private final List<FolderParameter<?>> removeParameters;

	public ParametersRemovedFromFolderEvent(FolderId folderId, List<FolderParameter<?>> removeParameters) {

		this.folderId = folderId;
		this.removeParameters = removeParameters;
	}

	public FolderId getFolderId() {
		return folderId;
	}

	public List<FolderParameter<?>> getRemoveParameters() {
		return removeParameters;
	}
}
