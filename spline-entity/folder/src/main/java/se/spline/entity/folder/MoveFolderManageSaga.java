package se.spline.entity.folder;


import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.saga.annotation.EndSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import se.spline.api.folder.CheckValidParentForFolderCommand;
import se.spline.api.folder.InvalidParentFolderEvent;
import se.spline.api.folder.MoveFolderCommand;
import se.spline.api.folder.MoveFolderStartedEvent;
import se.spline.api.folder.ValidParentFolderEvent;

import java.util.Collections;

public class MoveFolderManageSaga extends FolderManagerSaga {

	private static final long serialVersionUID = -7037430239870131271L;

	@StartSaga
	@SagaEventHandler(associationProperty = "folderId")
		public void handle(MoveFolderStartedEvent event) {
		setFolderId(event.getFolderId());
		setNewParentId(event.getNewParentId());
		final CheckValidParentForFolderCommand command = new CheckValidParentForFolderCommand(event.getNewParentId(), event.getFolderId());
		getCommandBus().dispatch(new GenericCommandMessage<>(CheckValidParentForFolderCommand.COMMAND, command, Collections.emptyMap()));
	}

	@SagaEventHandler(associationProperty = "folderId")
	@EndSaga
	public void handle(ValidParentFolderEvent event) {
		final MoveFolderCommand command = new MoveFolderCommand(getFolderId(), getNewParentId());
		getCommandBus().dispatch(new GenericCommandMessage<>(MoveFolderCommand.COMMAND, command, Collections.emptyMap()));
	}

	@SagaEventHandler(associationProperty = "folderId")
	@EndSaga
	public void handle(InvalidParentFolderEvent event) {

	}
}