package se.spline.api.document;

public class DocumentCreatedEvent {
	private final DocumentId id;
	private final String name;

	public DocumentCreatedEvent(DocumentId id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public DocumentId getId() {
		return id;
	}
}
