package se.spline.entity.folder;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FolderRepositoryConfiguration {

	@Bean
	@Autowired
	EventSourcingRepository<Folder> folderRepository(EventStore eventStore) {
		return new EventSourcingRepository<Folder>(Folder.class, eventStore);
	}
}
