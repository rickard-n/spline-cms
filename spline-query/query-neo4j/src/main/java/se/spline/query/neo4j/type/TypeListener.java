package se.spline.query.neo4j.type;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.type.event.TypeCreatedEvent;

@Component
public class TypeListener {
    private final TypeQueryRepository repository;

    @Autowired
    public TypeListener(TypeQueryRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void handle(TypeCreatedEvent event) {
        repository.save(TypeEntity.builder()
            .typeId(event.getTypeId().getIdentifier())
            .name(event.getName())
            .baseType(event.getBaseType())
            .build());
    }
}
