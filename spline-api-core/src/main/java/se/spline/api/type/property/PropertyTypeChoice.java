package se.spline.api.type.property;

import java.util.List;

public interface PropertyTypeChoice<T> {
    String getDisplayName();

    List<T> getValue();

    List<PropertyTypeChoice<T>> getChoice();
}
