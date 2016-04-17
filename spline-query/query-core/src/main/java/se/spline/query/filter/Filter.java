package se.spline.query.filter;

import lombok.Getter;

@Getter
public class Filter {
    private final String propertyName;
    private final Object propertyValue;

    public Filter(String propertyName, Object propertyValue) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

}
