package se.spline.query.neo4j.type;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.type.event.TypeCreatedEvent;
import se.spline.domain.Type;
import se.spline.query.type.TypeQueryRepository;

import java.util.Collections;

@Component
public class TypeListener {
    private final TypeQueryRepository repository;

    @Autowired
    public TypeListener(TypeQueryRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void handle(TypeCreatedEvent event) {
        repository.saveResource(Type.builder()
            .id(event.getTypeId())
            .name(event.getName())
            .baseType(event.getBaseType())
            .properties(Collections.emptyList())
            .build());
    }
}
