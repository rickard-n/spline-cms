package se.spline.entity.folder;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import se.spline.api.folder.CheckValidParentForFolderCommand;
import se.spline.api.folder.CreateFolderCommand;
import se.spline.api.folder.MoveFolderCommand;

@Component
public class FolderCommandHandler {
	private Repository<Folder> repository;

	@CommandHandler(commandName = "createFolderCommand")
	public void handleCreateFolder(CreateFolderCommand command) {
		Folder folder = new Folder(command.getFolderId(),
				command.getName(),
				command.getParentId());
		repository.add(folder);
	}

	@CommandHandler(commandName = MoveFolderCommand.COMMAND)
	public void handleMoveFolder(MoveFolderCommand command) {
		Assert.notNull(command.getFolderId(), "FolderIdentifier may not be null");
		Assert.notNull(command.getNewParentId(), "New parent FolderIdentifier may not be null");

		final Folder folder = repository.load(command.getFolderId());
		folder.move(command.getNewParentId());
	}

	@CommandHandler(commandName = CheckValidParentForFolderCommand.COMMAND)
	public void handleReserveCheckValidParentForFolderCommand(CheckValidParentForFolderCommand command) {
		final Folder folder = repository.load(command.getFolderId());
		folder.checkValidParent(command.getChildId());
	}

	@Autowired
	public void setRepository(Repository<Folder> repository) {
		this.repository = repository;
	}
}
