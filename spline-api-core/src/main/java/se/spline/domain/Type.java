package se.spline.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;
import se.spline.api.type.BaseType;
import se.spline.api.type.TypeId;
import se.spline.api.type.property.PropertyType;

import java.util.List;

@Builder
@Data
@Wither
public class Type {

    private final TypeId id;
    private final String name;
    private final String displayName;
    private final String description;

    private final BaseType baseType;
    private final TypeId parent;

    private final boolean creatable;
    private final boolean fulltextIndexed;
    private final List<PropertyType> properties;
}
