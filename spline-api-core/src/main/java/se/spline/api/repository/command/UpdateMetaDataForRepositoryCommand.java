package se.spline.api.repository.command;

import lombok.Getter;
import se.spline.api.repository.RepositoryId;
import se.spline.api.repository.RepositoryMetaData;

@Getter
public class UpdateMetaDataForRepositoryCommand extends RepositoryCommand {

    private final RepositoryMetaData metaData;

    public UpdateMetaDataForRepositoryCommand(RepositoryId repositoryId, RepositoryMetaData metaData) {
        super(repositoryId);
        this.metaData = metaData;
    }
}
