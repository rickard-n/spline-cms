package se.spline.api.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToMany;
import io.katharsis.resource.annotations.JsonApiToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonApiResource(type = "folder")
public class Folder {

    @JsonApiId
    private String id;
    private String name;
    private Date created;
    private Date updated;
    private Long version;

    @JsonApiToOne
    private Folder parent;
    @JsonApiToMany(lazy = false)
    private List<Folder> children;
    private Map<String, String> properties;

}
