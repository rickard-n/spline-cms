package se.spline.api.type.command;

import org.junit.Test;
import se.spline.api.type.BaseType;
import se.spline.api.type.TypeId;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CreateTypeCommandTest {

    @Test
    public void shouldSetDisplayNameToNameIfEmpty() {
        assertEquals("name", CreateTypeCommand.builder().name("name").build().getDisplayName());
    }

    @Test
    public void shouldReturnEmptyStringIfDescriptionIsNull() {
        assertEquals("", CreateTypeCommand.builder().build().getDescription());
    }

    @Test(expected = ValidationException.class)
    public void shouldNotAllowNameIsNull() {
        assertViolationOfPathName(CreateTypeCommand.builder().build(), "name");
    }

    @Test(expected = ValidationException.class)
    public void shouldNotAllowNameIsEmpty() {
        assertViolationOfPathName(CreateTypeCommand.builder().name("").build(), "name");
    }

    @Test(expected = ValidationException.class)
    public void shouldNotAllowIdIsNull() {
        assertViolationOfPathName(CreateTypeCommand.builder().build(), "id");
    }

    @Test(expected = ValidationException.class)
    public void shouldNotAllowBaseTypeIsNull() {
        assertViolationOfPathName(CreateTypeCommand.builder().build(), "baseType");
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotAllowPropertiesIsNull() {
        CreateTypeCommand.builder().properties(null).build();
    }

    @Test
    public void shouldBuildCorrectCommandIfAllRequiredFieldsAreProvided() {
        assertViolationOfPathName(
            CreateTypeCommand.builder().id(TypeId.from("1")).baseType(BaseType.FOLDER).name("name").properties(Collections.emptyList()).build(),
            "name", "id", "baseType", "properties"
            );
    }

    private void assertViolationOfPathName(Object object, String... pathNames) {
        final List<String> paths = Arrays.asList(pathNames);
        final Set<ConstraintViolation<Object>> constraintViolations =
            Validation.buildDefaultValidatorFactory().getValidator().validate(object);
        constraintViolations.forEach(violation ->
            violation.getPropertyPath().forEach(node -> {
                paths.forEach(pathName -> {
                    if (pathName.equals(node.getName())) {
                        throw new ValidationException(String.format("Found violation of path '%s'.", pathName));
                    }
                });
            }));
    }
}
