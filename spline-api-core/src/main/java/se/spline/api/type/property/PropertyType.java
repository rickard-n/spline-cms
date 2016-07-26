package se.spline.api.type.property;

import java.util.List;

public interface PropertyType<T> {
    String getName();
    String getQueryName();
    String getDisplayName();
    String getDescription();

    PropertyTypes getPropertyType();
    Cardinality getCardinality();
    Updatability getUpdatability();

    boolean isInherited();
    boolean isRequired();
    List<PropertyTypeChoice<T>> getChoice();
    boolean isOpenChoice();
    List<T> getDefaultValue();
}
