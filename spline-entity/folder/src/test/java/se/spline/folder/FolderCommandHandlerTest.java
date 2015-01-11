package se.spline.folder;

import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import se.spline.api.folder.CreateFolderCommand;
import se.spline.api.folder.FolderCreatedEvent;
import se.spline.api.folder.FolderId;
import se.spline.api.folder.FolderMovedEvent;
import se.spline.api.folder.MoveFolderCommand;
import se.spline.entity.folder.Folder;
import se.spline.entity.folder.FolderCommandHandler;

import java.util.Collections;

public class FolderCommandHandlerTest {

	private FixtureConfiguration<Folder> fixture;

	@Before
	public void setUp() {
		fixture = Fixtures.newGivenWhenThenFixture(Folder.class);
		FolderCommandHandler commandHandler = new FolderCommandHandler();
		commandHandler.setRepository(fixture.getRepository());
		fixture.registerAnnotatedCommandHandler(commandHandler);
	}

	@Test
	public void testCreateFolder() {
		CreateFolderCommand command = new CreateFolderCommand(new FolderId("id"), "TestItem", new FolderId("parentId"));

		fixture.given()
				.when(new GenericCommandMessage<>("createFolderCommand", command, Collections.emptyMap()))
				.expectEvents(new FolderCreatedEvent(new FolderId("id"), "TestItem", new FolderId("parentId")));
	}

	@Test
	public void shouldMoveFolderIfParentIsCorrect() {
		final FolderId id = new FolderId("id");
		final FolderId parentId = new FolderId("parentId");
		final FolderId newParentId = new FolderId("newParentId");
		final MoveFolderCommand command = new MoveFolderCommand(id, newParentId);

		fixture.
				given(new FolderCreatedEvent(id, "folder", parentId))
				.when(new GenericCommandMessage<>("moveFolderCommand", command, Collections.emptyMap()))
				.expectEvents(new FolderMovedEvent(id, newParentId));
	}
}