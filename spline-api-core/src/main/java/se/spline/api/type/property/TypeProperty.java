package se.spline.api.type.property;

import java.util.List;

public interface TypeProperty<T> {
    String getName();
    String getQueryName();
    String getDisplayName();
    String getDescription();

    PropertyType getPropertyType();
    Cardinality getCardinality();
    Updatability getUpdatability();

    boolean isInherited();
    boolean isRequired();
    List<PropertyChoiceType<T>> getChoice();
    boolean isOpenChoice();
    List<T> getDefaultValue();
}
