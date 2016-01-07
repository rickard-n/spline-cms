package se.spline.api;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.domain.IdentifierFactory;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class AbstractId implements Serializable {
    protected final String identifier;

    protected AbstractId() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
    }
}
