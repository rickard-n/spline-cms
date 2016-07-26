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
public class PropertyChoiceType {
    private String displayName;
    private List<Object> value;
    private List<PropertyChoiceType> choice;
}
