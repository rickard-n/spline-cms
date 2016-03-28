package se.spline.entity.repository;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.folder.FolderId;
import se.spline.api.folder.command.CreateRootFolderCommand;
import se.spline.api.repository.RepositoryData;
import se.spline.api.repository.command.UpdateRepositoryWithRootFolderCommand;
import se.spline.api.repository.event.RepositoryCreatedEvent;

@Component
public class RepositoryOperationListener {
    private final Logger logger = LoggerFactory.getLogger(RepositoryOperationListener.class);

    @Autowired
    private CommandBus commandBus;


    @EventHandler
    private void handleRepositoryCreated(RepositoryCreatedEvent event) {
        logger.debug("About to dispatch a new command to create a root folder for the repository '{}'.", event.getMetaData().getName());

        final FolderId folderId = new FolderId();
        final RepositoryData repositoryData = RepositoryData.builder().id(event.getId()).metaData(event.getMetaData()).build();
        final CreateRootFolderCommand createRootFolderCommand =
            new CreateRootFolderCommand(folderId, repositoryData);
        commandBus.dispatch(new GenericCommandMessage<>(createRootFolderCommand));

        final UpdateRepositoryWithRootFolderCommand updateRepositoryWithRootFolderCommand = new UpdateRepositoryWithRootFolderCommand(event.getId(), folderId);
            commandBus.dispatch(new GenericCommandMessage<>(updateRepositoryWithRootFolderCommand));
    }


}
