package se.spline.entity.folder;

import se.spline.api.folder.FolderId;
import se.spline.api.folder.event.FolderCreatedEvent;
import se.spline.api.repository.RepositoryData;

public class RootFolder extends Folder {

    RootFolder(FolderId folderId, RepositoryData repository) {
        apply(new FolderCreatedEvent(folderId, repository.getMetaData().getName(), null));
    }
}
