package se.spline.api.repository.event;

import lombok.Data;
import se.spline.api.repository.RepositoryId;

@Data
public class RepositoryDeletedEvent {
    private final RepositoryId id;
}
