package se.spline.api.type.property;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class IntegerPropertyTypeTest {

    @Test
    public void shouldCreateAnIntegerPropertyType() {
        assertNotNull(IntegerPropertyType.builder().build());
    }
}
