package se.spline.entity.folder;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

//@Configuration
public class FolderRepositoryConfiguration {

	@Bean
	@Autowired
	EventSourcingRepository<Folder> folderRepository(EventStore eventStore) {
		return new EventSourcingRepository<>(Folder.class, eventStore);
	}


}
