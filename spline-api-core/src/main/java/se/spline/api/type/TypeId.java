package se.spline.api.type;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Builder;
import se.spline.api.AbstractId;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TypeId extends AbstractId {
    private static final long serialVersionUID = 9181824122251237497L;

    public TypeId() {
        super();
    }

    @Builder()
    public TypeId(String identifier) {
        super(identifier);
    }

    public static TypeId from(String identifier) {
        return builder().identifier(identifier).build();
    }
}
