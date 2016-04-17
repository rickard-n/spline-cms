package se.spline.query.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.NoRepositoryBean;
import se.spline.query.KatharsisQueryRepository;

@NoRepositoryBean
public interface RepositoryQueryRepository extends GraphRepository<RepositoryEntity>, KatharsisQueryRepository<RepositoryEntity> {
    RepositoryEntity findByRepositoryId(String id);
    Iterable<RepositoryEntity> findAllByRepositoryId(Iterable<String> ids);
    void deleteByRepositoryId(String id);
}
