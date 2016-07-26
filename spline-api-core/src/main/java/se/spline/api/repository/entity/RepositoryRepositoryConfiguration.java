package se.spline.api.repository.entity;

import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.saga.GenericSagaFactory;
import org.axonframework.saga.ResourceInjector;
import org.axonframework.saga.SagaFactory;
import org.axonframework.saga.SagaManager;
import org.axonframework.saga.SagaRepository;
import org.axonframework.saga.annotation.AnnotatedSagaManager;
import org.axonframework.saga.spring.SpringResourceInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

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
    public SagaManager sagaManager(EventBus eventBus, SagaRepository sagaRepository) throws UnknownHostException {
        final AnnotatedSagaManager sagaManager = new AnnotatedSagaManager(sagaRepository, sagaFactory());
        eventBus.subscribe(sagaManager);
        return sagaManager;
    }

    public SagaFactory sagaFactory() {
        final GenericSagaFactory sagaFactory = new GenericSagaFactory();
        sagaFactory.setResourceInjector(resourceInjector());
        return sagaFactory;
    }

    @Bean
    public ResourceInjector resourceInjector() {
        return new SpringResourceInjector();
    }
}
