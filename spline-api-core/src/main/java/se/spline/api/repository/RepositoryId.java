package se.spline.api.repository;

import org.axonframework.domain.IdentifierFactory;

import java.io.Serializable;
import java.util.Objects;


public class RepositoryId implements Serializable {
    private static final long serialVersionUID = -3789552087809158083L;

    private final String identifier;
    private final int hashCode;

    public RepositoryId() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
        this.hashCode = identifier.hashCode();
    }

    public RepositoryId(String identifier) {
        this.identifier = identifier;
        this.hashCode = identifier.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepositoryId that = (RepositoryId) o;

        return Objects.equals(identifier, that.identifier);

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
