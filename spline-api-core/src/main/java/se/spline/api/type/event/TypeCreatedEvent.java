package se.spline.api.type.event;

import lombok.Getter;
import se.spline.api.type.BaseType;
import se.spline.api.type.TypeId;
import se.spline.api.type.property.PropertyType;

import java.util.List;

@Getter
public class TypeCreatedEvent {
	private final TypeId typeId;
	private final String name;
    private final BaseType baseType;
    private final List<PropertyType> properties;

    public TypeCreatedEvent(TypeId typeId, String name, BaseType baseType, List<PropertyType> properties) {
		this.typeId = typeId;

		this.name = name;
        this.baseType = baseType;
        this.properties = properties;
    }
}
