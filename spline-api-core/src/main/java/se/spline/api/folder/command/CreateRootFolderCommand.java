package se.spline.api.folder.command;

import lombok.Getter;
import se.spline.api.folder.FolderId;
import se.spline.api.repository.RepositoryData;

@Getter
public class CreateRootFolderCommand extends FolderCommand  {
    private final RepositoryData repositoryData;

    public CreateRootFolderCommand(FolderId id, RepositoryData repositoryData) {
        super(id);
        this.repositoryData = repositoryData;
    }
}
