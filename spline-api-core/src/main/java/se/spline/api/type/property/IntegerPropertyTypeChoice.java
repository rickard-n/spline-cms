package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class IntegerPropertyTypeChoice extends PropertyTypeChoiceImpl<BigInteger> {

    @Builder
    public IntegerPropertyTypeChoice(String displayName, List<BigInteger> value, List<PropertyTypeChoice<BigInteger>> choice) {
        super(displayName, value, choice);
    }
}
