package se.spline.query.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface RepositoryQueryRepository extends GraphRepository<RepositoryEntity> {
    RepositoryEntity findByRepositoryId(String repositoryId);
}
