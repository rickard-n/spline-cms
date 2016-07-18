package se.spline.entity.type;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.annotation.AnnotationCommandTargetResolver;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TypeRepositoryConfiguration {

	@Bean
	@Autowired
	EventSourcingRepository<Type> typeRepository(EventStore eventStore, EventBus eventBus) {
        final EventSourcingRepository<Type> repository = new EventSourcingRepository<>(Type.class, eventStore);
        repository.setEventBus(eventBus);
        return repository;
	}

	@Bean
    @Autowired
    AggregateAnnotationCommandHandler typeAggregateAnnotationCommandHandler(CommandBus commandBus, Repository<Type> repository) {
        return AggregateAnnotationCommandHandler.subscribe(Type.class, repository, commandBus, new AnnotationCommandTargetResolver());
    }
}
