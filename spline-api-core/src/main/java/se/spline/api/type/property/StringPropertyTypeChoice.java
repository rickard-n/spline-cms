package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StringPropertyTypeChoice extends PropertyTypeChoiceImpl<String> {
    @Builder
    public StringPropertyTypeChoice(String displayName, List<String> value, List<PropertyTypeChoice<String>> choice) {
        super(displayName, value, choice);
    }
}
