package se.spline.query.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import se.spline.api.repository.RepositoryId;

public interface RepositoryQueryRepository extends PagingAndSortingRepository<RepositoryEntity, RepositoryId> {
}
