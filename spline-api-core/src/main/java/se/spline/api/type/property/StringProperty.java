package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StringProperty extends TypePropertyImpl<String> {

    @Builder
    public StringProperty(String name, String queryName, String displayName, String description, PropertyType propertyType, Cardinality cardinality, List<PropertyChoiceType<String>> choice, List<String> defaultValue, Updatability updatability, boolean inherited, boolean required, boolean openChoice) {
        super(name, queryName, displayName, description, propertyType, cardinality, choice, defaultValue, updatability, inherited, required, openChoice);
    }
}
