package se.spline.api.type.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Singular;
import lombok.Builder;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import se.spline.api.type.BaseType;
import se.spline.api.type.TypeId;
import se.spline.api.type.property.PropertyType;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class CreateTypeCommand {
    @NotNull(message = "id is null.")
    private final TypeId id;
	@NotBlank(message = "name is empty or blank.")
    private final String name;
    @NotNull(message = "baseType is null.")
    private final BaseType baseType;

    @NotEmpty
    @Singular
    private List<PropertyType> properties;
}
