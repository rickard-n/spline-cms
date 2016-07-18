package se.spline.api.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiLookupIncludeAutomatically;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToMany;
import io.katharsis.resource.annotations.JsonApiToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@JsonApiResource(type = "folder")
public class Folder {

    @JsonApiId
    private String id;
    private String name;
    private Date created;
    private Date updated;
    private Long version;

    @JsonApiToOne
    @JsonApiLookupIncludeAutomatically
    private Folder parent;
    @JsonApiToMany(lazy = false)
    @JsonApiLookupIncludeAutomatically
    private List<Folder> children;

    @JsonApiToMany(lazy = false)
    @JsonApiLookupIncludeAutomatically
    private List<Property> properties;


}
