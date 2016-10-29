package se.spline.api.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import se.spline.api.model.fragment.TypeProperty;
import se.spline.api.type.BaseType;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@JsonApiResource(type = "type")
public class TypeModel {

    @JsonApiId
    private String id;
    private String name;
    private BaseType baseType;

    @Singular
    private List<TypeProperty> properties;
}
