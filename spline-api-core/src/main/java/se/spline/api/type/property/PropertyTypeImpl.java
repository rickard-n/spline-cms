package se.spline.api.type.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Singular;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
abstract class PropertyTypeImpl<T> implements PropertyType<T>,Serializable {
    private final String name;
    private final String queryName;
    private final String displayName;
    private final String description;
    private final PropertyTypes propertyType;
    private final Cardinality cardinality;
    @Singular(value = "choice")
    private final List<PropertyTypeChoice<T>> choice;
    @Singular(value = "defaultValue")
    private final List<T> defaultValue;
    private final Updatability updatability;
    private final boolean inherited;
    private final boolean required;
    private final boolean openChoice;
}
