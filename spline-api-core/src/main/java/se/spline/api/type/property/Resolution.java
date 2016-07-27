package se.spline.api.type.property;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Resolution {
    YEAR("year"), DATE("date"), TIME("time");
    private final String value;

    private static class LookupMap {
        static Map<String, Resolution> name = new HashMap<>();
    }


    Resolution(String value) {
        this.value = value;
        Resolution.LookupMap.name.put(value, this);
    }

    public static Resolution fromValue(String value) {
        return Resolution.LookupMap.name.get(value);
    }
}
