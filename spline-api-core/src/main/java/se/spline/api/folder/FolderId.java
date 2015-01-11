package se.spline.api.folder;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import java.io.Serializable;

public class FolderId implements Serializable {

	private static final long serialVersionUID = -3623665207829207096L;
	private final String identifier;
	private final int hashCode;

	public FolderId() {
		this.identifier = IdentifierFactory.getInstance().generateIdentifier();
		this.hashCode = identifier.hashCode();
	}

	public FolderId(String identifier) {
		Assert.notNull(identifier, "Identifier may not be null");
		this.identifier = identifier;
		this.hashCode = identifier.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FolderId folderId = (FolderId) o;

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
