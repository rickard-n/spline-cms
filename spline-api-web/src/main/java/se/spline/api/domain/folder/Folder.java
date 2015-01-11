package se.spline.api.domain.folder;

import se.spline.api.folder.FolderId;

import java.io.Serializable;

public class Folder implements Serializable {
	private FolderId id;
	private String name;
	private FolderId parentId;
	private long version;

	public Folder(FolderId id, String name, FolderId parentId) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.version = System.currentTimeMillis();
	}


	public FolderId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public FolderId getParentId() {
		return parentId;
	}

	public long getVersion() {
		return version;
	}
}
