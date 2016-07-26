package se.spline.api.type.property;

import java.util.HashMap;
import java.util.Map;

public enum Cardinality {
    SINGLE,MULTI;

    private static class LookupMap {
        static Map<String, Cardinality> name = new HashMap<>();
    }

    Cardinality() {
        LookupMap.name.put(this.name(), this);
    }

    public static Cardinality fromValue(String name) {
        return LookupMap.name.get(name);
    }
}
