package se.spline.query.folder;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class FolderEntry {

	@Id
	@javax.persistence.Id
	private String id;
	private String name;
	private String parentId;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
