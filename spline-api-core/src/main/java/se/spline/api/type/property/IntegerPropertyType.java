package se.spline.api.type.property;

import java.math.BigInteger;
import java.util.List;

public class IntegerPropertyType extends PropertyTypeImpl<BigInteger> {

    public IntegerPropertyType(String name, String queryName, String displayName, String description, Cardinality cardinality, List<PropertyTypeChoice<BigInteger>> choice, List<BigInteger> defaultValue, Updatability updatability, boolean inherited, boolean required, boolean openChoice) {
        super(name, queryName, displayName, description, PropertyTypes.INTEGER, cardinality, choice, defaultValue, updatability, inherited, required, openChoice);
    }
}
