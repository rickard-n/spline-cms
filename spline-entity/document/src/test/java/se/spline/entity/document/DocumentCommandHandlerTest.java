package se.spline.entity.document;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import se.spline.api.document.CreateDocumentCommand;
import se.spline.api.document.DocumentCreatedEvent;
import se.spline.api.document.DocumentId;

public class DocumentCommandHandlerTest {

	private FixtureConfiguration<Document> fixture;

	@Before
	public void setUp() {
		fixture = Fixtures.newGivenWhenThenFixture(Document.class);
		DocumentCommandHandler commandHandler = new DocumentCommandHandler();
		commandHandler.setRepository(fixture.getRepository());
		fixture.registerAnnotatedCommandHandler(commandHandler);
	}

	@Test
	public void shouldCreateNewDocument() {
		CreateDocumentCommand command = new CreateDocumentCommand(new DocumentId("id"), "TestItem");

		fixture.given()
				.when(command)
				.expectEvents(new DocumentCreatedEvent(new DocumentId("id"), "TestItem"));

	}
}
