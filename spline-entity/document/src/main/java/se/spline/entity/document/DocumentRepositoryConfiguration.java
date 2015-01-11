package se.spline.entity.document;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentRepositoryConfiguration {

	@Bean
	@Autowired
	EventSourcingRepository<Document> documentRepository(EventStore eventStore) {
		return new EventSourcingRepository<>(Document.class, eventStore);
	}
}
