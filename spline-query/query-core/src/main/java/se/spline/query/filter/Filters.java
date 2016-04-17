package se.spline.query.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Filters implements Iterable<Filter> {

    private List<Filter> filters = new ArrayList<>();

    public Filters add(Filter... filters) {
        for (Filter filter : filters) {
            this.filters.add(filter);
        }
        return this;
    }

    public Filters add(String key, Object value) {
        this.filters.add(new Filter(key, value));
        return this;
    }


    @Override
    public Iterator<Filter> iterator() {
        return filters.iterator();
    }

    @Override
    public void forEach(Consumer<? super Filter> action) {
        filters.forEach(action);
    }

    @Override
    public Spliterator<Filter> spliterator() {
        return filters.spliterator();
    }
}
