package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.ResourceRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Component;
import se.spline.api.model.Type;
import se.spline.api.model.fragment.PropertyChoiceType;
import se.spline.api.repository.builder.TypeFactory;
import se.spline.api.type.TypeId;
import se.spline.api.type.command.CreateTypeCommand;
import se.spline.api.type.property.Cardinality;
import se.spline.api.type.property.PropertyType;
import se.spline.api.type.property.PropertyTypeChoiceImpl;
import se.spline.api.type.property.StringPropertyType;
import se.spline.api.type.property.StringPropertyTypeChoice;
import se.spline.api.type.property.Updatability;
import se.spline.query.neo4j.type.TypeQueryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TypeResourceRepository implements ResourceRepository<Type, String> {
    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private TypeQueryRepository typeQueryRepository;

    @Autowired
    private Neo4jOperations template;

    @Override
    public Type findOne(String id, QueryParams queryParams) {
        return TypeFactory.from(typeQueryRepository.findByDocumentId(id));
    }

    @Override
    public Iterable<Type> findAll(QueryParams queryParams) {
        return TypeFactory.from(typeQueryRepository.findAll());
    }

    @Override
    public Iterable<Type> findAll(Iterable<String> ids, QueryParams queryParams) {
        return TypeFactory.from(typeQueryRepository.findAllByDocumentId(ids));
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public  Type save(Type entity) {
        final CreateTypeCommand.CreateTypeCommandBuilder commandBuilder = CreateTypeCommand.builder().id(new TypeId()).name(entity.getName()).baseType(entity.getBaseType());
        final List<PropertyType> properties = entity.getProperties().stream()
            .map(typeProperty -> StringPropertyType.builder()
                .name(typeProperty.getName())
                .description(typeProperty.getDescription())
                .displayName(typeProperty.getDisplayName())
                .updatability(Updatability.fromValue(typeProperty.getUpdatability()))
                .openChoice(typeProperty.isOpenChoice())
                .defaultValue(typeProperty.getDefaultValue().stream().map(object -> (String) object).collect(Collectors.toList()))
                .inherited(typeProperty.isInherited())
                .queryName(typeProperty.getQueryName())
                .choice(typeProperty.getChoice().stream().map(this::buildStringChoice).collect(Collectors.toList()))
                .cardinality(Cardinality.fromValue(typeProperty.getCardinality()))
                .build())
            .collect(Collectors.toList());
        commandBuilder.properties(properties);
        return findOne(((TypeId)commandGateway.sendAndWait(commandBuilder.build())).getIdentifier(), null);
    }

    private PropertyTypeChoiceImpl<String> buildStringChoice(PropertyChoiceType choice) {
        return StringPropertyTypeChoice.builder()
            .displayName(choice.getDisplayName())
            .choice(choice.getChoice().stream().map(this::buildStringChoice).collect(Collectors.toList()))
            .value(choice.getValue().stream().map(String::valueOf).collect(Collectors.toList()))
            .build();
    }
}
