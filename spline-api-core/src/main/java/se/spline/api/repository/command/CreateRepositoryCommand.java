package se.spline.api.repository.command;

import lombok.Getter;
import se.spline.api.repository.RepositoryId;

@Getter
public class CreateRepositoryCommand extends RepositoryCommand {
    private final String name;

    public CreateRepositoryCommand(RepositoryId repositoryId, String name) {
        super(repositoryId);
        this.name = name;
    }
}
