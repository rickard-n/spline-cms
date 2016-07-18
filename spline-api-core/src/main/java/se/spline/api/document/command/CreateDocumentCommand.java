package se.spline.api.document.command;

import se.spline.api.document.DocumentId;

public class CreateDocumentCommand extends DocumentCommand {
	private final String name;

	public CreateDocumentCommand(DocumentId id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
