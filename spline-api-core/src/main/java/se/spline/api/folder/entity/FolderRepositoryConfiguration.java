package se.spline.api.folder.entity;

import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FolderRepositoryConfiguration {

	@Bean
	@Autowired
	EventSourcingRepository<Folder> folderRepository(EventStore eventStore, EventBus eventBus) {
        final EventSourcingRepository<Folder> repository = new EventSourcingRepository<>(Folder.class, eventStore);
        repository.setEventBus(eventBus);
        return repository;
	}


}
