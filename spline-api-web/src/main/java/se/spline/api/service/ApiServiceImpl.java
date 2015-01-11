package se.spline.api.service;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.springframework.beans.factory.annotation.Autowired;
import se.spline.api.domain.Folder.Folder;
import se.spline.api.folder.CreateFolderCommand;
import se.spline.api.folder.FolderId;
import se.spline.api.folder.MoveFolderCommand;

import java.util.Collections;

public class ApiServiceImpl implements ApiService {

	@Autowired
	CommandBus commandBus;

	@Override
	public void addFolder(Folder folder) {
		final CreateFolderCommand createFolderCommand = new CreateFolderCommand(folder.getId(), folder.getName(), folder.getParentId());
		commandBus.dispatch(new GenericCommandMessage<>("createFolderCommand", createFolderCommand, Collections.emptyMap()));
	}

	@Override
	public void moveFolder(String folderId, String parentId) {
		final MoveFolderCommand moveFolderCommand = new MoveFolderCommand(new FolderId(folderId), new FolderId(parentId));
		commandBus.dispatch(new GenericCommandMessage<>("moveFolderCommand", moveFolderCommand, Collections.emptyMap()));
	}
}
