package se.spline.api.type.property;

import java.util.HashMap;
import java.util.Map;

public enum Updatability {
    READONLY,READWRITE,WHENCHECKOUT,ONCREATE;

    private static class LookupMap {
        static Map<String, Updatability> name = new HashMap<>();
    }

    Updatability() {
        LookupMap.name.put(this.name(), this);
    }

    public static Updatability fromValue(String value) {
        return LookupMap.name.get(value);
    }
}
