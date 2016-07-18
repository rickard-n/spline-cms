package se.spline.api.document;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Builder;
import se.spline.api.AbstractId;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DocumentId extends AbstractId {

	private static final long serialVersionUID = 9181824122251237497L;

    public DocumentId() {
        super();
    }

    @Builder()
    public DocumentId(String identifier) {
        super(identifier);
    }

    public static DocumentId from(String identifier) {
        return builder().identifier(identifier).build();
    }
}
