package se.spline.api.repository.entity;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.annotation.AnnotationCommandTargetResolver;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryRepositoryConfiguration {

    @Bean
    @Autowired
    EventSourcingRepository<Repository> repositoryRepository(EventStore eventStore, EventBus eventBus) {
        final EventSourcingRepository<Repository> repository = new EventSourcingRepository<>(Repository.class, eventStore);
        repository.setEventBus(eventBus);
        return repository;
    }

    @Bean
    @Autowired
    AggregateAnnotationCommandHandler repositoryAggregateAnnotationCommandHandler(CommandBus commandBus, org.axonframework.repository.Repository<Repository> repository) {
        return AggregateAnnotationCommandHandler.subscribe(Repository.class, repository, commandBus, new AnnotationCommandTargetResolver());
    }
}
