package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.URI;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UriPropertyTypeChoice extends PropertyTypeChoiceImpl<URI> {

    @Builder
    public UriPropertyTypeChoice(String displayName, List<URI> value, List<PropertyTypeChoice<URI>> choice) {
        super(displayName, value, choice);
    }
}
