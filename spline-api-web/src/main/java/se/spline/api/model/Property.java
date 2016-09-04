package se.spline.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@JsonApiResource(type = "property")
public class Property {

    @JsonApiId
    private String id;
    private PropertyType type;
    private String key;
    private Object value;

    public enum PropertyType {
        STRING("string"), BOOLEAN("boolean"), DATE("date");

        private String name;

        PropertyType(String name) {
            this.name = name;
        }

        @JsonValue
        public String getName() {
            return name;
        }
    }
}
