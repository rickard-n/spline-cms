package se.spline.api.repository.builder;

import se.spline.api.model.Folder;
import se.spline.query.neo4j.folder.FolderEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FolderRelationFactory {
    public static Folder from(FolderEntity entity) {
        return buildFolder(entity.getFolderId(), entity.getName());
    }

    public static List<Folder> from(Iterable<FolderEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
            .map(FolderRelationFactory::from)
            .collect(Collectors.toList());
    }

    private static Folder buildFolder(String id, String name) {
        return Folder.builder().id(id).name(name).build();
    }

}
