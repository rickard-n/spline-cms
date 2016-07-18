package se.spline.entity.document;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import se.spline.api.document.DocumentCreatedEvent;
import se.spline.api.document.DocumentId;
import se.spline.api.document.command.CreateDocumentCommand;

public class DocumentTest {

    private FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(Document.class);
    }

    @Test
    public void shouldCreateEventOnCreateCommand() {
        fixture.given()
            .when(new CreateDocumentCommand(DocumentId.from("document-1"), "document name"))
            .expectEvents(new DocumentCreatedEvent(DocumentId.from("document-1"), "document name"));

    }
}
