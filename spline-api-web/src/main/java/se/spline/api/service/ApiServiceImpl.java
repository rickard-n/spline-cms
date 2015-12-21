package se.spline.api.service;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import se.spline.api.domain.folder.Folder;
import se.spline.api.folder.FolderId;
import se.spline.api.folder.command.AddParametersToFolderCommand;
import se.spline.api.folder.command.CreateFolderCommand;
import se.spline.api.folder.command.DeleteFolderCommand;
import se.spline.api.folder.parameter.FolderParameter;
import se.spline.api.folder.parameter.StringFolderParameter;
import se.spline.query.folder.FolderEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiServiceImpl implements ApiService {

	@Autowired
	CommandBus commandBus;

	@Autowired
	CommandGateway commandGateway;

	@Override
	public void addFolder(Folder folder) {
		final CreateFolderCommand createFolderCommand = new CreateFolderCommand(folder.getId(), folder.getName(), folder.getParentId());
		commandGateway.sendAndWait(createFolderCommand);
	}

	@Override
	public void addProperties(FolderEntry folder, Map<String, String> map) {
		final List<FolderParameter<?>> prop = new ArrayList<>(map.size());
		for(Map.Entry<String, String> entry : map.entrySet()) {
			prop.add(new StringFolderParameter(entry.getKey(), entry.getValue()));
		}
		final AddParametersToFolderCommand command = new AddParametersToFolderCommand(new FolderId(folder.getId()), prop);
		commandGateway.sendAndWait(command);
	}

	@Override
	public void deleteFolder(FolderEntry folder) {
		final DeleteFolderCommand command = new DeleteFolderCommand(new FolderId(folder.getId()));
		commandGateway.sendAndWait(command);
	}
}
