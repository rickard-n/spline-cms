package se.spline.api.repository.builder;

import se.spline.api.model.Folder;
import se.spline.query.neo4j.folder.FolderEntity;

import java.util.Collections;
import java.util.List;

public class FolderFactory {

    public static Folder from(FolderEntity entity) {
        return Folder.builder()
            .id(entity.getFolderId())
            .name(entity.getName())
            .created(entity.getCreated())
            .updated(entity.getUpdated())
            .properties(entity.getProperties())
            .children(getChildren(entity))
            .parent(getParent(entity)).build();
    }

    private static Folder getParent(FolderEntity entity) {
        return entity.getParent() != null ? FolderRelationFactory.from(entity.getParent()) : null;
    }

    private static List<Folder> getChildren(FolderEntity entity) {
        //if(entity.getChildren() == null) {
            return Collections.emptyList();
        /*}
        return entity.getChildren().stream()
            .filter(child -> child != null)
            .map(FolderRelationFactory::from)
            .collect(Collectors.toList());*/
    }
}
