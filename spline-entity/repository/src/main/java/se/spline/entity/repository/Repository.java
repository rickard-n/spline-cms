package se.spline.entity.repository;

import lombok.Getter;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import se.spline.api.folder.FolderId;
import se.spline.api.repository.RepositoryId;
import se.spline.api.repository.RepositoryMetaData;
import se.spline.api.repository.event.RepositoryCreatedEvent;
import se.spline.api.repository.event.RepositoryRootFolderChangedEvent;

@Getter
public class Repository extends AbstractAnnotatedAggregateRoot<RepositoryId> {

    @AggregateIdentifier
    private RepositoryId id;
    private RepositoryMetaData metaData;
    private FolderId rootFolder;

    public Repository(RepositoryId repositoryId, RepositoryMetaData metaData) {
        apply(new RepositoryCreatedEvent(repositoryId, metaData));
    }

    @SuppressWarnings("UnusedDeclaration")
    protected Repository() {}

    @Override
    public RepositoryId getIdentifier() {
        return id;
    }

    public void changeRootFolder(FolderId folderId) {
        apply(new RepositoryRootFolderChangedEvent(id, folderId));
    }

    @EventHandler
    public void handle(RepositoryCreatedEvent event) {
        this.id = event.getId();
    }

    @EventHandler
    public void handle(RepositoryRootFolderChangedEvent event) {
        this.rootFolder = event.getFolderId();
    }
}
