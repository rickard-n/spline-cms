package se.spline.api.repository.command;

import lombok.Getter;
import se.spline.api.repository.RepositoryId;
import se.spline.api.repository.RepositoryMetaData;

@Getter
public class CreateRepositoryCommand extends RepositoryCommand {
    private final RepositoryMetaData metaData;

    public CreateRepositoryCommand(RepositoryId repositoryId, RepositoryMetaData metaData) {
        super(repositoryId);
        this.metaData = metaData;
    }
}
