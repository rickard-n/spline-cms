package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DecimalPropertyTypeChoice extends PropertyTypeChoiceImpl<BigDecimal> {

    @Builder
    public DecimalPropertyTypeChoice(String displayName, List<BigDecimal> value, List<PropertyTypeChoice<BigDecimal>> choice) {
        super(displayName, value, choice);
    }
}
