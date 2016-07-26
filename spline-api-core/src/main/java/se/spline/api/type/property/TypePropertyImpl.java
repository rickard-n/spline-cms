package se.spline.api.type.property;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
abstract class TypePropertyImpl<T> implements TypeProperty<T>,Serializable {
    private String name;
    private String queryName;
    private String displayName;
    private String description;
    private PropertyType propertyType;
    private Cardinality cardinality;
    private List<PropertyChoiceType<T>> choice;
    private List<T> defaultValue;
    private Updatability updatability;
    private boolean inherited;
    private boolean required;
    private boolean openChoice;
}
