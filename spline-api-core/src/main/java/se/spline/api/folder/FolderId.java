package se.spline.api.folder;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Builder;
import se.spline.api.AbstractId;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FolderId extends AbstractId {

	private static final long serialVersionUID = -3623665207829207096L;

    public FolderId() {
        super();
    }

    @Builder()
    public FolderId(String identifier) {
        super(identifier);
    }

    public static FolderId from(String identifier) {
        return builder().identifier(identifier).build();
    }
}
