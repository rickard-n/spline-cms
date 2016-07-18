package se.spline.query.neo4j;

import org.neo4j.ogm.cypher.query.Pagination;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.GraphRepositoryImpl;
import org.springframework.data.neo4j.template.Neo4jOgmExceptionTranslator;
import org.springframework.data.neo4j.template.Neo4jOperations;
import se.spline.query.KatharsisQueryRepository;
import se.spline.query.filter.Filters;

import java.util.Collection;



public abstract class Neo4jKatharsisQueryRepository<T> extends GraphRepositoryImpl<T> implements GraphRepository<T>, KatharsisQueryRepository<T> {

    private Class<T> clazz;

    private final Session session;

    public Neo4jKatharsisQueryRepository(Class<T> clazz, Session session, Neo4jOperations operations) {
        super(clazz, operations);
        this.session = session;
        this.clazz = clazz;
    }

    @Override
    public Collection<T> findAll(Filters filters, Sort sort) {
        try {
            return session.loadAll(clazz, buildFilters(filters), buildSortOrder(sort));
        } catch (Exception e) {
            throw Neo4jOgmExceptionTranslator.translateExceptionIfPossible(e);
        }
    }

    @Override
    public Collection<T> findAll(Filters filters, Pageable page) {
        try {
            return session.loadAll(clazz, buildFilters(filters), buildPagination(page));
        } catch (Exception e) {
            throw Neo4jOgmExceptionTranslator.translateExceptionIfPossible(e);
        }
    }

    private Pagination buildPagination(Pageable page) {
        return new Pagination(page.getPageNumber(), page.getPageSize());
    }

    private SortOrder buildSortOrder(Sort sort) {
        final SortOrder sortOrder = new SortOrder();
        sort.forEach(order -> sortOrder.add(SortOrder.Direction.valueOf(order.getDirection().name()), order.getProperty()) );
        return sortOrder;
    }

    private org.neo4j.ogm.cypher.Filters buildFilters(Filters filters) {
        final org.neo4j.ogm.cypher.Filters neoFilter = new org.neo4j.ogm.cypher.Filters();
        filters.forEach(filter -> neoFilter.add(filter.getPropertyName(), filter.getPropertyValue()));
        return neoFilter;
    }
}
