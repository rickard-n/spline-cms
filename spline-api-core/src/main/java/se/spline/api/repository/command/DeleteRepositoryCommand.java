package se.spline.api.repository.command;

import lombok.Getter;
import se.spline.api.repository.RepositoryId;

@Getter
public class DeleteRepositoryCommand extends RepositoryCommand {
    public DeleteRepositoryCommand(RepositoryId repositoryId) {
        super(repositoryId);
    }
}
