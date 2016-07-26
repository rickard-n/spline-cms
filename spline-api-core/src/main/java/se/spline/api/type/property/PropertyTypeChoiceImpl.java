package se.spline.api.type.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@AllArgsConstructor
public abstract class PropertyTypeChoiceImpl<T> implements PropertyTypeChoice<T> {
    private final String displayName;
    @Singular("value")
    private final List<T> value;
    @Singular("choice")
    private final List<PropertyTypeChoice<T>> choice;
}
