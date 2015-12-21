package se.spline.api.request.folder;

import org.hibernate.validator.constraints.NotBlank;

//@ApiModel(value = "Folder", description = "Folder representation.")
public class FolderRequest {

	@NotBlank(message = "Name must not be blank!")
	//@ApiModelProperty(required = true)
	String name;
	String id;
	String parentId;

	public FolderRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
