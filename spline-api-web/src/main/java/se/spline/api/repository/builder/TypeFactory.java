package se.spline.api.repository.builder;

import se.spline.api.model.Type;
import se.spline.query.neo4j.type.TypeEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TypeFactory {

    public static Type from(TypeEntity entity) {
        return Type.builder()
            .id(entity.getTypeId())
            .name(entity.getName())
            .baseType(entity.getBaseType())
            .build();
    }

    public static List<Type> from(Iterable<TypeEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
            .map(TypeFactory::from)
            .collect(Collectors.toList());
    }
}
