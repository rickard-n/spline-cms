package se.spline.api.repository.builder;

import se.spline.api.model.TypeModel;
import se.spline.api.model.fragment.TypeProperty;
import se.spline.api.type.property.PropertyType;
import se.spline.domain.Type;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TypeFactory {

    public static TypeModel from(Type entity) {
        return TypeModel.builder()
            .id(entity.getId().getIdentifier())
            .name(entity.getName())
            .baseType(entity.getBaseType())
            .properties(entity.getProperties().stream()
                .map(TypeFactory::from)
                .collect(Collectors.toList()))
            .build();
    }

    private static TypeProperty from(PropertyType propertyType) {
        return TypeProperty.builder()
            //.name(propertyType.getName())
            //.displayName(propertyType.getDisplayName())
            //.propertyType(propertyType.getPropertyType().toString())
            .build();
    }

    public static List<TypeModel> from(Iterable<Type> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
            .map(TypeFactory::from)
            .collect(Collectors.toList());
    }
}
