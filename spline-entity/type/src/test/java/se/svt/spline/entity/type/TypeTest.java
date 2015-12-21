package se.svt.spline.entity.type;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import se.spline.api.type.command.CreateTypeCommand;
import se.spline.api.type.event.TypeCreatedEvent;
import se.spline.entity.type.Type;

public class TypeTest {

	private FixtureConfiguration<Type> fixture;

	@Before
	public void setUp() throws Exception {
		fixture = Fixtures.newGivenWhenThenFixture(Type.class);
	}

	//@Test
	public void testCreateToDoItem() throws Exception {
		fixture.given()
				.when(new CreateTypeCommand("type"))
				.expectEvents(new TypeCreatedEventMatcher("type"));
	}

	private class TypeCreatedEventMatcher extends BaseMatcher<TypeCreatedEvent> {
		private final String type;

		public TypeCreatedEventMatcher(String type) {
			this.type = type;
		}

		@Override
		public boolean matches(Object o) {
			return ((TypeCreatedEvent) o).getName().equals(type);
		}

		@Override
		public void describeTo(Description description) {

		}
	}
}
