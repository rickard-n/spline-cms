package se.spline.api.document;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import java.io.Serializable;

public class DocumentId implements Serializable {

	private static final long serialVersionUID = 9181824122251237497L;
	private final String identifier;
	private final int hashCode;

	public DocumentId() {
		this.identifier = IdentifierFactory.getInstance().generateIdentifier();
		this.hashCode = identifier.hashCode();
	}

	public DocumentId(String identifier) {
		Assert.notNull(identifier, "Identifier may not be null");
		this.identifier = identifier;
		this.hashCode = identifier.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DocumentId folderId = (DocumentId) o;

		return identifier.equals(folderId.identifier);

	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public String toString() {
		return identifier;
	}
}
