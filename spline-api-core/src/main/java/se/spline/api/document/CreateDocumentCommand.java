package se.spline.api.document;

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
