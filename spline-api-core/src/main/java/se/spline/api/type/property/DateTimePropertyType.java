package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DateTimePropertyType extends PropertyTypeImpl<ZonedDateTime> {

    private final Resolution resolution;

    @Builder
    public DateTimePropertyType(String name, String queryName, String displayName, String description, Cardinality cardinality, List<PropertyTypeChoice<ZonedDateTime>> choice, List<ZonedDateTime> defaultValue, Updatability updatability, boolean inherited, boolean required, boolean openChoice, Resolution resolution) {
        super(name, queryName, displayName, description, PropertyTypes.DATETIME, cardinality, choice, defaultValue, updatability, inherited, required, openChoice);
        this.resolution = resolution;
    }
}
