package se.spline.api.repository.entity;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import se.spline.api.repository.RepositoryId;
import se.spline.api.repository.RepositoryMetaData;
import se.spline.api.repository.command.CreateRepositoryCommand;
import se.spline.api.repository.command.DeleteRepositoryCommand;
import se.spline.api.repository.command.UpdateMetaDataForRepositoryCommand;
import se.spline.api.repository.event.RepositoryCreatedEvent;
import se.spline.api.repository.event.RepositoryDeletedEvent;
import se.spline.api.repository.event.RepositoryMetaDataUpdatedEvent;

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
        final RepositoryMetaData repositoryMetaData = RepositoryMetaData.builder().name("TestItem").description("TestDescription").build();
        CreateRepositoryCommand command = new CreateRepositoryCommand(id, repositoryMetaData);

        fixture.given()
            .when(command)
            .expectEvents(new RepositoryCreatedEvent(id, repositoryMetaData));
    }

    @Test
    public void shouldDeleteRepository() {
        final RepositoryId id = new RepositoryId();
        fixture.given(new RepositoryCreatedEvent(id, RepositoryMetaData.builder().name("ItemToDelete").build()))
            .when(new DeleteRepositoryCommand(id))
            .expectEvents(new RepositoryDeletedEvent(id));
    }

    @Test
    public void shouldChangeRepositoryMetaData() {
        final RepositoryId id = new RepositoryId();
        final RepositoryMetaData metaData = RepositoryMetaData.builder().name("newName").description("newDiscription").build();
        fixture.given(new RepositoryCreatedEvent(id, RepositoryMetaData.builder().name("oldName").build()))
            .when(new UpdateMetaDataForRepositoryCommand(id, metaData))
            .expectEvents(new RepositoryMetaDataUpdatedEvent(id, metaData));

    }
}
