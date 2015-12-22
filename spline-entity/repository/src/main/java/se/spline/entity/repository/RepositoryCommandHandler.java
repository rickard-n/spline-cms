package se.spline.entity.repository;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.repository.command.CreateRepositoryCommand;

@Component
public class RepositoryCommandHandler {

    private org.axonframework.repository.Repository<Repository> repository;

    @Autowired
    public void setRepository(org.axonframework.repository.Repository<Repository> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handleCreateRepository(CreateRepositoryCommand command) {
        Repository repository = new Repository(command.getRepositoryId(),
            command.getName());
        this.repository.add(repository);
    }
}
