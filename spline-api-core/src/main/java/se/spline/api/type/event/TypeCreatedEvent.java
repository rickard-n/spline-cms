package se.spline.api.type.event;

import se.spline.api.type.TypeId;

public class TypeCreatedEvent {
	private final TypeId typeId;
	private final String name;

	public TypeCreatedEvent(TypeId typeId, String name) {
		this.typeId = typeId;

		this.name = name;
	}

	public TypeId getTypeId() {
		return typeId;
	}

	public String getName() {
		return name;
	}
}
