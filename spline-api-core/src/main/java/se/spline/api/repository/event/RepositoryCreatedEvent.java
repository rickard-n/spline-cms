package se.spline.api.repository.event;

import lombok.Data;
import se.spline.api.repository.RepositoryId;

@Data
public class RepositoryCreatedEvent {
    private final RepositoryId id;
    private final String name;
}
