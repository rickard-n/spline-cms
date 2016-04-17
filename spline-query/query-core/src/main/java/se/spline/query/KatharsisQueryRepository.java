package se.spline.query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import se.spline.query.filter.Filters;

import java.util.Collection;

public interface KatharsisQueryRepository<T> {
    Collection<T> findAll(Filters filters, Sort sort);
    Collection<T> findAll(Filters filters, Pageable page);
}
