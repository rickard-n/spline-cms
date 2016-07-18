package se.spline.api.type.event;

import lombok.Getter;
import se.spline.api.type.BaseType;
import se.spline.api.type.TypeId;

@Getter
public class TypeCreatedEvent {
	private final TypeId typeId;
	private final String name;
    private final BaseType baseType;

    public TypeCreatedEvent(TypeId typeId, String name, BaseType baseType) {
		this.typeId = typeId;

		this.name = name;
        this.baseType = baseType;
    }
}
