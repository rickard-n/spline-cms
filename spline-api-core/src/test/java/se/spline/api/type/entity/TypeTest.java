package se.spline.api.type.entity;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.interceptors.BeanValidationInterceptor;
import org.axonframework.commandhandling.interceptors.JSR303ViolationException;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import se.spline.api.type.BaseType;
import se.spline.api.type.TypeId;
import se.spline.api.type.command.CreateTypeCommand;
import se.spline.api.type.event.TypeCreatedEvent;

import java.util.Collections;

public class TypeTest {

	private FixtureConfiguration<Type> fixture;

	@Before
	public void setUp() throws Exception {
		fixture = Fixtures.newGivenWhenThenFixture(Type.class);
	}

	@Test
	public void shouldCreateEventWithDefaults() throws Exception {
		fixture.given()
				.when(buildValidCommand())
                .expectReturnValue(TypeId.from("1"))
				.expectEvents(new TypeCreatedEvent(
				    TypeId.from("1"),
                    "type",
                    "type",
                    "",
                    BaseType.DOCUMENT,
                    null,
                    false,
                    false,
                    Collections.emptyList()));
	}

    private CreateTypeCommand buildValidCommand() {
        return CreateTypeCommand.builder().id(TypeId.from("1")).name("type").baseType(BaseType.DOCUMENT).properties(Collections.emptyList()).build();
    }

    @Test(expected = JSR303ViolationException.class)
    public void shouldValidateCommand() {
        final SimpleCommandBus commandBus = (SimpleCommandBus) fixture.getCommandBus();
        commandBus.setDispatchInterceptors(Collections.singletonList(new BeanValidationInterceptor()));
        fixture.given()
            .when(CreateTypeCommand.builder().build());

    }
}
