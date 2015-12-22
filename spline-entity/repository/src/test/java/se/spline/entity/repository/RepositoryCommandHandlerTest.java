package se.spline.entity.repository;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import se.spline.api.repository.RepositoryId;
import se.spline.api.repository.command.CreateRepositoryCommand;
import se.spline.api.repository.event.RepositoryCreatedEvent;

public class RepositoryCommandHandlerTest {

    private FixtureConfiguration<Repository> fixture;

    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(Repository.class);
        RepositoryCommandHandler commandHandler = new RepositoryCommandHandler();
        commandHandler.setRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }

    @Test
    public void shouldCreateNewRepository() {
        final RepositoryId id = new RepositoryId();
        CreateRepositoryCommand command = new CreateRepositoryCommand(id, "TestItem");

        fixture.given()
            .when(command)
            .expectEvents(new RepositoryCreatedEvent(id, "TestItem"));

    }
}
