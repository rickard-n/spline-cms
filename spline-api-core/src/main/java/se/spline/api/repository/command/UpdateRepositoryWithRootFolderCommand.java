package se.spline.api.repository.command;

import lombok.Getter;
import se.spline.api.folder.FolderId;
import se.spline.api.repository.RepositoryId;

@Getter
public class UpdateRepositoryWithRootFolderCommand extends RepositoryCommand {

    private final FolderId folderId;

    public UpdateRepositoryWithRootFolderCommand(RepositoryId repositoryId, FolderId folderId) {
        super(repositoryId);
        this.folderId = folderId;
    }
}
