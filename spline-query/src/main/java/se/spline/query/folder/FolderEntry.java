package se.spline.query.folder;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import java.util.HashMap;
import java.util.Map;

@Entity
public class FolderEntry {

	@Id
	@javax.persistence.Id
	private String id;
	private String name;
	private String parentId;

	@ElementCollection()
	@MapKeyColumn(name="key")
	@Column(name="value")
	private Map<String, String> properties;

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

	public Map<String, String> getProperties() {
		if(properties == null) {
			properties = new HashMap<>();
		}
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "FolderEntry{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", parentId='" + parentId + '\'' +
				", properties=" + properties +
				'}';
	}

}
