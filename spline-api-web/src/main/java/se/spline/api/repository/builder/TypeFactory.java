package se.spline.api.repository.builder;

import se.spline.api.model.Type;
import se.spline.api.model.fragment.TypeProperty;
import se.spline.query.neo4j.type.TypeEntity;
import se.spline.query.neo4j.type.TypePropertyEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TypeFactory {

    public static Type from(TypeEntity entity) {
        return Type.builder()
            .id(entity.getTypeId())
            .name(entity.getName())
            .baseType(entity.getBaseType())
            .properties(entity.getProperties().stream()
                .map(TypeFactory::from)
                .collect(Collectors.toList()))
            .build();
    }

    private static TypeProperty from(TypePropertyEntity propertyType) {
        return TypeProperty.builder()
            //.name(propertyType.getName())
            //.displayName(propertyType.getDisplayName())
            //.propertyType(propertyType.getPropertyType().toString())
            .build();
    }

    public static List<Type> from(Iterable<TypeEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
            .map(TypeFactory::from)
            .collect(Collectors.toList());
    }
}
