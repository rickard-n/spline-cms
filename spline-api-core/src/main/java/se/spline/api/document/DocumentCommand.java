package se.spline.api.document;

public abstract class DocumentCommand {

	private final DocumentId documentId;


	protected DocumentCommand(DocumentId documentId) {
		this.documentId = documentId;
	}

	public DocumentId getDocumentId() {
		return documentId;
	}
}
