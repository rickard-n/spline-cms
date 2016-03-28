package se.spline.api.repository.event;

import lombok.Data;
import se.spline.api.folder.FolderId;
import se.spline.api.repository.RepositoryId;

@Data
public class RepositoryRootFolderChangedEvent {
    private final RepositoryId repositoryId;
    private final FolderId folderId;
}
