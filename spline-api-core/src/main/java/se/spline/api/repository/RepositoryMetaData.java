package se.spline.api.repository;

import lombok.Data;
import lombok.NonNull;
import lombok.Builder;

@Builder
@Data
public class RepositoryMetaData {
    @NonNull
    private final String name;
    private final String description;
}
