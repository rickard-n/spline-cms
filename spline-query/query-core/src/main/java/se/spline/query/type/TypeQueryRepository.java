package se.spline.query.type;

import se.spline.domain.Type;
import se.spline.query.KatharsisQueryRepository;

public interface TypeQueryRepository extends KatharsisQueryRepository<Type> {
    Type findByTypeId(String typeId);
    Iterable<Type> findAllByTypeId(Iterable<String> typeId);
}
