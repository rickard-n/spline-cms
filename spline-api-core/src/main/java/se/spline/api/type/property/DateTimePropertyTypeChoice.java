package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DateTimePropertyTypeChoice extends PropertyTypeChoiceImpl<ZonedDateTime> {

    @Builder
    public DateTimePropertyTypeChoice(String displayName, List<ZonedDateTime> value, List<PropertyTypeChoice<ZonedDateTime>> choice) {
        super(displayName, value, choice);
    }
}
