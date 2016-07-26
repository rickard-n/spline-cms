package se.spline.api.type.property;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DecimalPropertyType extends PropertyTypeImpl<BigDecimal> {

    @Builder
    public DecimalPropertyType(String name, String queryName, String displayName, String description, Cardinality cardinality, List<PropertyTypeChoice<BigDecimal>> choice, List<BigDecimal> defaultValue, Updatability updatability, boolean inherited, boolean required, boolean openChoice) {
        super(name, queryName, displayName, description, PropertyTypes.DECIMAL, cardinality, choice, defaultValue, updatability, inherited, required, openChoice);
    }
}
