package se.spline.api.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@JsonApiResource(type = "repository")
public class Repository {

    @JsonApiId
    private String id;
    private String name;
    private String description;
    @JsonApiToOne
    private Folder rootFolder;

    private Date created;
    private Date updated;
    private Long version;

}
