package se.spline.api.repository.builder;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.spline.api.folder.FolderId;
import se.spline.api.model.Folder;

@Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor(staticName = "builder")
public class FolderRelationBuilder {
    private FolderId id;


    public Folder build() {
        return Folder.builder().id(id.getIdentifier()).build();
    }

}
