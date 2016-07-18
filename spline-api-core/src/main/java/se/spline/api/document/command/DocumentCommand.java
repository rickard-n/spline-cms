package se.spline.api.document.command;

import se.spline.api.document.DocumentId;

public abstract class DocumentCommand {

	private final DocumentId documentId;


	protected DocumentCommand(DocumentId documentId) {
		this.documentId = documentId;
	}

	public DocumentId getDocumentId() {
		return documentId;
	}
}
