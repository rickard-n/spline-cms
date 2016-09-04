package se.spline.query.neo4j.folder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity(label = "Folder")
public class FolderEntity {

    @GraphId
	private Long id;

    private String folderId;

    private String name;
    @Relationship(type="PUBLISHED_IN", direction=Relationship.OUTGOING)
	private FolderEntity parent;

    private Date created;
    private Date updated;

	private Map<String, String> properties;
}
