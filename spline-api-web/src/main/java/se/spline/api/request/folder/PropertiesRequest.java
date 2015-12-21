package se.spline.api.request.folder;

import javax.validation.constraints.NotNull;
import java.util.List;

//@ApiModel(value = "Properties", description = "Properties representation.")
public class PropertiesRequest {
	//@ApiModelProperty(required = true)
	@NotNull
	private List<PropertyRequest> properties;

	public PropertiesRequest() {
	}

	public List<PropertyRequest> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyRequest> properties) {
		this.properties = properties;
	}
}
