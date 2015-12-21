package se.spline.api.type.command;

public class CreateTypeCommand {
	private final String name;

	public CreateTypeCommand(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
