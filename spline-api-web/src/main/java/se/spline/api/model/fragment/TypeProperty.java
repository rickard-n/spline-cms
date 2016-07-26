package se.spline.api.model.fragment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
@Builder
@AllArgsConstructor
public class TypeProperty {
    private String name;
    private String queryName;
    private String displayName;
    private String description;
    private String propertyType;
    private String cardinality;
    private List<PropertyChoiceType> choice;
    private List<Object> defaultValue;
    private String updatability;
    private boolean inherited;
    private boolean required;
    private boolean openChoice;
}
