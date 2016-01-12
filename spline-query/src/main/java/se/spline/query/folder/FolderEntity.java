package se.spline.query.folder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;
import lombok.experimental.Delegate;
import org.springframework.data.annotation.Id;
import se.spline.api.folder.FolderId;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FolderEntity {

	@Id
	@javax.persistence.Id
	private FolderId id;
	private String name;
	private FolderId parentId;

    private interface FolderCollection {
        boolean add(FolderId item);
        boolean remove(FolderId item);
        boolean addAll(List<FolderId> items);
    }
    @Delegate(types={FolderCollection.class})
    private List<FolderId> children = new ArrayList<>();

	@ElementCollection()
	@MapKeyColumn(name="key")
	@Column(name="value")
	private Map<String, String> properties;


}
