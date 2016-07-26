package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.URI;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UriPropertyType extends PropertyTypeImpl<URI> {

    @Builder
    public UriPropertyType(String name, String queryName, String displayName, String description, Cardinality cardinality, List<PropertyTypeChoice<URI>> choice, List<URI> defaultValue, Updatability updatability, boolean inherited, boolean required, boolean openChoice) {
        super(name, queryName, displayName, description, PropertyTypes.URI, cardinality, choice, defaultValue, updatability, inherited, required, openChoice);
    }
}
