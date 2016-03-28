package se.spline.api.repository.command;

import lombok.Getter;
import lombok.NonNull;
import se.spline.api.repository.RepositoryId;
import se.spline.api.repository.RepositoryMetaData;

@Getter
public class CreateRepositoryCommand extends RepositoryCommand {
    @NonNull
    private final RepositoryMetaData metaData;

    public CreateRepositoryCommand(RepositoryId repositoryId, RepositoryMetaData metaData) {
        super(repositoryId);
        this.metaData = metaData;
    }
}
