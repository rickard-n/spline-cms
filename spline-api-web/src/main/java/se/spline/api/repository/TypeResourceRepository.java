package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.ResourceRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Component;
import se.spline.api.model.Type;
import se.spline.api.repository.builder.TypeFactory;
import se.spline.api.type.TypeId;
import se.spline.api.type.command.CreateTypeCommand;
import se.spline.query.neo4j.type.TypeQueryRepository;

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
        return findOne(((TypeId)commandGateway.sendAndWait(new CreateTypeCommand(new TypeId(), entity.getName(), entity.getBaseType()))).getIdentifier(), null);
    }
}
