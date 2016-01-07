package se.spline.api.folder.event;

import lombok.Getter;
import se.spline.api.folder.FolderId;
import se.spline.api.repository.RepositoryData;
import se.spline.api.repository.RepositoryId;
import se.spline.api.repository.RepositoryMetaData;

@Getter
public class RootFolderCreatedEvent {
    private final FolderId folderId;
    private final RepositoryId repositoryId;
    private final RepositoryMetaData repository;

    public RootFolderCreatedEvent(FolderId folderId, RepositoryData repository) {

        this.folderId = folderId;
        this.repositoryId = repository.getId();
        this.repository = repository.getMetaData();
    }
}
