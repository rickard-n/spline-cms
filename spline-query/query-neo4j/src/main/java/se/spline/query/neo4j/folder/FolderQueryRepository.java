package se.spline.query.neo4j.folder;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.NoRepositoryBean;
import se.spline.query.KatharsisQueryRepository;

@NoRepositoryBean
public interface FolderQueryRepository extends GraphRepository<FolderEntity>, KatharsisQueryRepository<FolderEntity> {

    FolderEntity findByFolderId(String folderId);
    Iterable<FolderEntity> findAllByFolderId(Iterable<String> folderId);

    Iterable<FolderEntity> findChildrenForFolderWithId(String folderId);
}
