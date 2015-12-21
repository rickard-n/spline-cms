package se.spline.api.type;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import java.io.Serializable;

public class TypeId implements Serializable {
	private final String identifier;
	private final int hashCode;

	public TypeId() {
		this.identifier = IdentifierFactory.getInstance().generateIdentifier();
		this.hashCode = identifier.hashCode();
	}

	public TypeId(String identifier) {
		Assert.notNull(identifier, "Identifier may not be null");
		this.identifier = identifier;
		this.hashCode = identifier.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TypeId folderId = (TypeId) o;

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
