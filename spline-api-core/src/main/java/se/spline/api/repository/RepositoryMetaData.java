package se.spline.api.repository;

import lombok.Data;
import lombok.experimental.Builder;

@Builder
@Data
public class RepositoryMetaData {
    private final String name;
    private final String description;
}
