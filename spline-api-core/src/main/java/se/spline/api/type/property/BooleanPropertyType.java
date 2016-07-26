package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BooleanPropertyType extends PropertyTypeImpl<Boolean> {

    @Builder
    public BooleanPropertyType(String name, String queryName, String displayName, String description, Cardinality cardinality, List<PropertyTypeChoice<Boolean>> choice, List<Boolean> defaultValue, Updatability updatability, boolean inherited, boolean required, boolean openChoice) {
        super(name, queryName, displayName, description, PropertyTypes.BOOLEAN, cardinality, choice, defaultValue, updatability, inherited, required, openChoice);
    }
}
