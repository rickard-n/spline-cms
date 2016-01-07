package se.spline.api.repository.event;

import lombok.Getter;
import se.spline.api.folder.FolderId;
import se.spline.api.repository.RepositoryId;

@Getter
public class RepositoryRootFolderChangedEvent {
    private final FolderId folderId;
    private final RepositoryId repositoryId;

    public RepositoryRootFolderChangedEvent(RepositoryId repositoryId, FolderId folderId) {

        this.repositoryId = repositoryId;
        this.folderId = folderId;
    }
}
