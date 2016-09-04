package se.spline.api.repository;

import lombok.Data;
import lombok.Builder;

@Builder
@Data
public class RepositoryData {
    private final RepositoryId id;
    private final RepositoryMetaData metaData;
}
