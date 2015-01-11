package se.spline.api.folder.parameter;

public class StringFolderParameter implements FolderParameter<String> {

	private final String name;
	private final String value;

	public StringFolderParameter(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
