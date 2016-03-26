package se.spline.query.neo4j.folder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import lombok.experimental.Delegate;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import se.spline.api.folder.FolderId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	private FolderId parentId;

    private Date created;
    private Date updated;
    private Long version;

    private interface FolderCollection {
        boolean add(FolderEntity item);
        boolean remove(FolderEntity item);
        boolean addAll(List<FolderEntity> items);
    }
    @Delegate(types={FolderCollection.class})
    @Relationship(type="CHILDREN", direction=Relationship.OUTGOING)
    private List<FolderEntity> children = new ArrayList<>();

	private Map<String, String> properties;
}
