package se.spline.query.neo4j.type;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.NoRepositoryBean;
import se.spline.query.KatharsisQueryRepository;

@NoRepositoryBean
public interface TypeQueryRepository extends GraphRepository<TypeEntity>, KatharsisQueryRepository<TypeEntity> {
    TypeEntity findByDocumentId(String typeId);
    Iterable<TypeEntity> findAllByDocumentId(Iterable<String> typeId);
}
