package se.spline.api.type.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;
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

    private final String displayName;
    private final String description;

    @NotNull(message = "baseType is null.")
    private final BaseType baseType;

    private final TypeId parent;
    private final boolean creatable;
    private final boolean fulltextIndexed;

    @NotNull
    @Singular
    private List<PropertyType> properties;

    public String getDisplayName() {
        return StringUtils
            .isEmpty(displayName) ? name : displayName;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }
}
