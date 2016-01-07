package se.spline.api.repository;

import lombok.Data;
import lombok.experimental.Builder;

@Builder
@Data
public class RepositoryData {
    private final RepositoryId id;
    private final RepositoryMetaData metaData;
}
