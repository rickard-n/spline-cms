package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StringPropertyType extends PropertyTypeImpl<String> {

    private final Integer maxLength;

    @Builder
    public StringPropertyType(String name, String queryName, String displayName, String description, Cardinality cardinality, List<PropertyTypeChoice<String>> choice, List<String> defaultValue, Updatability updatability, boolean inherited, boolean required, boolean openChoice, Integer maxLength) {
        super(name, queryName, displayName, description, PropertyTypes.STRING, cardinality, choice, defaultValue, updatability, inherited, required, openChoice);
        this.maxLength = maxLength;
    }
}
