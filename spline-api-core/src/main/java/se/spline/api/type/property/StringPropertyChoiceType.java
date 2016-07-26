package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StringPropertyChoiceType extends PropertyChoiceTypeImpl<String> {
    @Builder
    public StringPropertyChoiceType(String displayName, List<String> value, List<PropertyChoiceType<String>> choice) {
        super(displayName, value, choice);
    }
}
