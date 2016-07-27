package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class IntegerPropertyType extends PropertyTypeImpl<BigInteger> {

    private final BigInteger minValue;
    private final BigInteger maxValue;

    @Builder
    public IntegerPropertyType(String name, String queryName, String displayName, String description, Cardinality cardinality, List<PropertyTypeChoice<BigInteger>> choice, List<BigInteger> defaultValue, Updatability updatability, boolean inherited, boolean required, boolean openChoice, BigInteger minValue, BigInteger maxValue) {
        super(name, queryName, displayName, description, PropertyTypes.INTEGER, cardinality, choice, defaultValue, updatability, inherited, required, openChoice);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
}
