package se.spline.api.repository;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RepositoryIdTest {

    @Test
    public void shouldCreateARandomIdentifier() {
        final String identifier = new RepositoryId().getIdentifier();
        assertNotNull(identifier);
        assertTrue(identifier.length() > 0);
    }

    @Test
    public void shouldCreatedIdWithFixedIdentifier() {
        final String identifier = "identifier";
        final RepositoryId repositoryId = RepositoryId.builder().identifier(identifier).build();
        assertEquals(identifier, repositoryId.getIdentifier());
    }

    @Test
    public void shouldIdsWithSameIdentifierShouldBeEqual() {
        final String identifier = "identifier";
        final RepositoryId repositoryId1 = RepositoryId.builder().identifier(identifier).build();
        final RepositoryId repositoryId2 = RepositoryId.builder().identifier(identifier).build();
        assertEquals(repositoryId1, repositoryId2);
    }
}
