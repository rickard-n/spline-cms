package se.spline.api.request.folder;

import org.hibernate.validator.constraints.NotBlank;

//@ApiModel(value = "Property", description = "Property representation.")
public class PropertyRequest {
	@NotBlank(message = "Name must not be blank!")
	//@ApiModelProperty(required = true)
	private String name;
	private String value;

	public PropertyRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
