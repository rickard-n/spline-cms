package se.spline.entity.folder;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.folder.command.AddParametersToFolderCommand;
import se.spline.api.folder.command.CreateFolderCommand;
import se.spline.api.folder.command.DeleteFolderCommand;
import se.spline.api.folder.command.RemoveParameterFromFolderCommand;

@Component
public class FolderCommandHandler {
	private Repository<Folder> repository;

	@CommandHandler(commandName = CreateFolderCommand.COMMAND)
	public void handleCreateFolder(CreateFolderCommand command) {
		Folder folder = new Folder(command.getFolderId(),
				command.getName(),
				command.getParentId());
		repository.add(folder);
	}

	@CommandHandler(commandName = AddParametersToFolderCommand.COMMAND)
	public void handleAddParametersToFolder(AddParametersToFolderCommand command) {
		final Folder folder = repository.load(command.getFolderId());
		folder.addParameters(command.getParameters());
	}

	@CommandHandler(commandName = RemoveParameterFromFolderCommand.COMMAND)
	public void handleRemoveParametersFromFolder(RemoveParameterFromFolderCommand command) {
		final Folder folder = repository.load(command.getFolderId());
		folder.removeParameters(command.getParameters());
	}

	@CommandHandler(commandName = DeleteFolderCommand.COMMAND)
	public void handleDeleteFolderCommand(DeleteFolderCommand command) {
		final Folder folder = repository.load(command.getFolderId());
		folder.delete();
	}

	@Autowired
	public void setRepository(Repository<Folder> repository) {
		this.repository = repository;
	}
}
