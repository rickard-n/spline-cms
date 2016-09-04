package se.spline.api.repository.command;

import lombok.Getter;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import se.spline.api.repository.RepositoryId;

@Getter
public abstract class RepositoryCommand {
    @TargetAggregateIdentifier
    private final RepositoryId repositoryId;

    protected RepositoryCommand(RepositoryId repositoryId) {
        this.repositoryId = repositoryId;
    }

}
