package se.spline.api.repository.builder;

import se.spline.api.model.Folder;
import se.spline.query.neo4j.folder.FolderEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FolderFactory {

    public static Folder from(FolderEntity entity) {
        return Folder.builder()
            .id(entity.getFolderId())
            .name(entity.getName())
            .created(entity.getCreated())
            .updated(entity.getUpdated())
            .properties(entity.getProperties())
            .parent(getParent(entity)).build();
    }

    public static List<Folder> from(Iterable<FolderEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
            .map(FolderFactory::from)
            .collect(Collectors.toList());
    }

    private static Folder getParent(FolderEntity entity) {
        return entity.getParent() != null ? FolderRelationFactory.from(entity.getParent()) : null;
    }

}
