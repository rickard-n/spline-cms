package se.spline.folder;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import se.spline.api.folder.FolderId;
import se.spline.api.folder.command.AddParametersToFolderCommand;
import se.spline.api.folder.command.CreateFolderCommand;
import se.spline.api.folder.command.DeleteFolderCommand;
import se.spline.api.folder.command.RemoveParameterFromFolderCommand;
import se.spline.api.folder.event.FolderCreatedEvent;
import se.spline.api.folder.event.FolderDeletedEvent;
import se.spline.api.folder.event.ParametersAddedToFolderEvent;
import se.spline.api.folder.event.ParametersRemovedFromFolderEvent;
import se.spline.api.folder.parameter.FolderParameter;
import se.spline.api.folder.parameter.StringFolderParameter;
import se.spline.entity.folder.Folder;
import se.spline.entity.folder.FolderCommandHandler;

import java.util.ArrayList;
import java.util.List;

public class FolderCommandHandlerTest {

	private static final String NAME = "TestItem";
	private FixtureConfiguration<Folder> fixture;
	private FolderId folderId;
	private FolderId parentId;
	private List<FolderParameter<?>> parameters;

	@Before
	public void setUp() {
		fixture = Fixtures.newGivenWhenThenFixture(Folder.class);
		FolderCommandHandler commandHandler = new FolderCommandHandler();
		commandHandler.setRepository(fixture.getRepository());
		fixture.registerAnnotatedCommandHandler(commandHandler);
		folderId = new FolderId("id");
		parentId = new FolderId("parentId");
		parameters = new ArrayList<>();
		parameters.add(new StringFolderParameter("parameter1", "value1"));
		parameters.add(new StringFolderParameter("parameter2", "value2"));
	}

	@Test
	public void shouldSendFolderCreatedEventWhenCreateFolderCommand() {
		CreateFolderCommand command = new CreateFolderCommand(folderId, NAME, parentId);

		fixture.given()
				.when(command)
				.expectEvents(new FolderCreatedEvent(folderId, NAME, parentId));
	}

	@Test
	public void shouldSendFolderDeletedEventWhenDeleteFolderCommand() {
		DeleteFolderCommand command = new DeleteFolderCommand(folderId);

		fixture.given(new FolderCreatedEvent(folderId, NAME, null))
				.when(command)
				.expectEvents(new FolderDeletedEvent(folderId));
	}

	@Test
	public void shouldSendParameterAddedToFolderEventWhenAddParameterToFolderCommand() {
		final AddParametersToFolderCommand command = new AddParametersToFolderCommand(folderId, parameters);
		fixture.given(new FolderCreatedEvent(folderId, NAME, null))
				.when(command)
				.expectEvents(new ParametersAddedToFolderEvent(folderId, parameters));
	}

	@Test
	public void shouldSendParametersRemovedFromFolderWhenRemoveParametersCommand() {
		List<FolderParameter<?>> removeParameters = new ArrayList<>();
		removeParameters.add(new StringFolderParameter("parameter2", "value2"));
		final RemoveParameterFromFolderCommand command = new RemoveParameterFromFolderCommand(folderId, removeParameters);
		fixture.given(new FolderCreatedEvent(folderId, NAME, null), new ParametersAddedToFolderEvent(folderId, parameters))
				.when(command)
				.expectEvents(new ParametersRemovedFromFolderEvent(folderId, removeParameters));

	}
}
