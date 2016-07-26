package se.spline.api.type.property;

import java.util.List;

public interface PropertyChoiceType<T> {
    String getDisplayName();

    List<T> getValue();

    List<PropertyChoiceType<T>> getChoice();
}
