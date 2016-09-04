package se.spline.api.repository.entity;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import se.spline.api.folder.FolderId;
import se.spline.api.repository.RepositoryId;
import se.spline.api.repository.RepositoryMetaData;
import se.spline.api.repository.command.CreateRepositoryCommand;
import se.spline.api.repository.command.DeleteRepositoryCommand;
import se.spline.api.repository.command.UpdateMetaDataForRepositoryCommand;
import se.spline.api.repository.command.UpdateRepositoryWithRootFolderCommand;
import se.spline.api.repository.event.RepositoryCreatedEvent;
import se.spline.api.repository.event.RepositoryDeletedEvent;
import se.spline.api.repository.event.RepositoryMetaDataUpdatedEvent;
import se.spline.api.repository.event.RepositoryRootFolderChangedEvent;

public class Repository extends AbstractAnnotatedAggregateRoot<RepositoryId> {

    @AggregateIdentifier
    private RepositoryId id;
    private RepositoryMetaData metaData;
    private FolderId rootFolder;
    private boolean deleted;

    @CommandHandler
    public Repository(CreateRepositoryCommand command) {
        this.id = command.getRepositoryId();
        apply(new RepositoryCreatedEvent(
            command.getRepositoryId(),
            command.getMetaData()
        ));
    }

    @SuppressWarnings("UnusedDeclaration")
    public Repository() {}

    @Override
    public RepositoryId getIdentifier() {
        return id;
    }


    @CommandHandler
    public void handle(UpdateRepositoryWithRootFolderCommand command) {
        apply(new RepositoryRootFolderChangedEvent(id, command.getFolderId()));
    }

    @CommandHandler
    public void handle(DeleteRepositoryCommand command) {
        apply(new RepositoryDeletedEvent(id));
    }

    @CommandHandler
    public void handle(UpdateMetaDataForRepositoryCommand command) {
        apply(new RepositoryMetaDataUpdatedEvent(id, command.getMetaData()));
    }

    @EventSourcingHandler
    public void on(RepositoryCreatedEvent event) {
        this.id = event.getId();
        this.metaData = RepositoryMetaData.builder()
                .name(event.getMetaData()
                .getName()).description(event.getMetaData().getDescription())
            .build();
    }

    @EventSourcingHandler
    public void on(RepositoryRootFolderChangedEvent event) {
        this.rootFolder = event.getFolderId();
    }

    @EventSourcingHandler
    public void on(RepositoryDeletedEvent event) {
        this.deleted = true;
    }

    @EventSourcingHandler
    public void on(RepositoryMetaDataUpdatedEvent event) {
        this.metaData = event.getMetaData();
    }
}
