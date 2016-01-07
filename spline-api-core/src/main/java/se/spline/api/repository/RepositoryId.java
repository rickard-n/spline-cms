package se.spline.api.repository;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Builder;
import se.spline.api.AbstractId;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RepositoryId extends AbstractId {
    private static final long serialVersionUID = -3789552087809158083L;

    public RepositoryId() {
        super();
    }

    @Builder()
    public RepositoryId(String identifier) {
        super(identifier);
    }
}
