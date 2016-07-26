package se.spline.api.type.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@AllArgsConstructor
public abstract class PropertyChoiceTypeImpl<T> implements PropertyChoiceType<T> {
    private String displayName;
    @Singular("value")
    private List<T> value;
    @Singular("choice")
    private List<PropertyChoiceType<T>> choice;
}
