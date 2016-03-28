package se.spline.api.repository.builder;

import se.spline.api.model.Folder;
import se.spline.query.neo4j.folder.FolderEntity;

public class FolderRelationFactory {
    public static Folder from(FolderEntity entity) {
        return buildFolder(entity.getFolderId(), entity.getName());
    }

    private static Folder buildFolder(String id, String name) {
        return Folder.builder().id(id).name(name).build();
    }

}
