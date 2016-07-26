package se.spline.api.document.entity;

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
public class DocumentRepositoryConfiguration {

	@Bean
	@Autowired
	EventSourcingRepository<Document> documentRepository(EventStore eventStore, EventBus eventBus) {
        final EventSourcingRepository<Document> repository = new EventSourcingRepository<>(Document.class, eventStore);
        repository.setEventBus(eventBus);
        return repository;
	}

	@Bean
    @Autowired
    AggregateAnnotationCommandHandler documentAggregateAnnotationCommandHandler(CommandBus commandBus, Repository<Document> repository) {
        return AggregateAnnotationCommandHandler.subscribe(Document.class, repository, commandBus, new AnnotationCommandTargetResolver());
    }
}
