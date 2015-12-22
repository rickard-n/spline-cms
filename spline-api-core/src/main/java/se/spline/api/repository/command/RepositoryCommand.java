package se.spline.api.repository.command;

import lombok.Getter;
import se.spline.api.repository.RepositoryId;

@Getter
public abstract class RepositoryCommand {
    private final RepositoryId repositoryId;

    protected RepositoryCommand(RepositoryId repositoryId) {
        this.repositoryId = repositoryId;
    }

}
