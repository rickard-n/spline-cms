package se.spline.api.type.command;

import org.hibernate.validator.constraints.NotBlank;
import se.spline.api.type.BaseType;
import se.spline.api.type.TypeId;

import javax.validation.constraints.NotNull;


public class CreateTypeCommand {
    @NotNull(message = "id is null.")
    private final TypeId id;
	@NotBlank(message = "name is empty or blank.")
    private final String name;
    @NotNull(message = "baseType is null.")
    private final BaseType baseType;

    public CreateTypeCommand(TypeId id, String name, BaseType baseType) {
        this.id = id;
        this.name = name;
        this.baseType = baseType;
    }

    public TypeId getId() {
        return id;
    }

    public String getName() {
		return name;
	}

    public BaseType getBaseType() {
        return baseType;
    }
}
