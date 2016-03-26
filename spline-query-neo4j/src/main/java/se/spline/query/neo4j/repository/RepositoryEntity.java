package se.spline.query.neo4j.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Relationship;
import se.spline.query.neo4j.folder.FolderEntity;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryEntity {

    @GraphId
    private Long id;

    private String repositoryId;

    private String name;
    private String description;
    @Relationship(type="ROOT_FOLDER", direction=Relationship.OUTGOING)
    private FolderEntity rootFolder;

    private Long version;
    private Date created;
    private Date updated;
}
