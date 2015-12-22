package se.spline.entity.repository;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import se.spline.api.repository.RepositoryId;
import se.spline.api.repository.event.RepositoryCreatedEvent;

public class Repository extends AbstractAnnotatedAggregateRoot<RepositoryId> {

    @AggregateIdentifier
    private RepositoryId id;

    public Repository(RepositoryId repositoryId, String name) {
        apply(new RepositoryCreatedEvent(repositoryId, name));
    }

    @SuppressWarnings("UnusedDeclaration")
    protected Repository() {}

    @Override
    public RepositoryId getIdentifier() {
        return id;
    }

    @EventHandler
    public void handle(RepositoryCreatedEvent event) {
        this.id = event.getId();
    }
}
