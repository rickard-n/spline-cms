package se.spline.query.neo4j.folder;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface FolderQueryRepository extends GraphRepository<FolderEntity> {

    FolderEntity findByFolderId(String folderId);
    Iterable<FolderEntity> findAllByFolderId(Iterable<String> folderId);
}
