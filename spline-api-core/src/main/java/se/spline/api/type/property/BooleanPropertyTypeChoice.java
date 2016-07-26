package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BooleanPropertyTypeChoice extends PropertyTypeChoiceImpl<Boolean> {

    @Builder
    public BooleanPropertyTypeChoice(String displayName, List<Boolean> value, List<PropertyTypeChoice<Boolean>> choice) {
        super(displayName, value, choice);
    }
}
