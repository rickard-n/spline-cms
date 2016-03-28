package se.spline.entity.repository;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.repository.command.CreateRepositoryCommand;
import se.spline.api.repository.command.DeleteRepositoryCommand;
import se.spline.api.repository.command.UpdateMetaDataForRepositoryCommand;
import se.spline.api.repository.command.UpdateRepositoryWithRootFolderCommand;

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
            command.getMetaData());
        this.repository.add(repository);
    }

    @CommandHandler
    public void handle(UpdateRepositoryWithRootFolderCommand command) {
        final Repository repository = this.repository.load(command.getRepositoryId());
        repository.changeRootFolder(command.getFolderId());
    }

    @CommandHandler
    public void handle(DeleteRepositoryCommand command) {
        final Repository repository = this.repository.load(command.getRepositoryId());
        repository.delete();
    }

    @CommandHandler
    public void handle(UpdateMetaDataForRepositoryCommand command) {
        final Repository repository = this.repository.load(command.getRepositoryId());
        repository.updateMetadata(command.getMetaData());
    }
}
