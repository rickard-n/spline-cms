package se.spline.api.type.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import se.spline.api.type.BaseType;
import se.spline.api.type.TypeId;
import se.spline.api.type.property.PropertyType;

import java.util.List;

@Getter
@AllArgsConstructor
public class TypeCreatedEvent {
	private final TypeId typeId;
	private final String name;
    private final String displayName;
    private final String description;
    private final BaseType baseType;
    private final TypeId parent;
    private final boolean creatable;
    private final boolean fulltextIndexed;
    private final List<PropertyType> properties;
}
