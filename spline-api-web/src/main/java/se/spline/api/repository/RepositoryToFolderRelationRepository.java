package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.model.Folder;
import se.spline.api.model.Repository;
import se.spline.query.neo4j.folder.FolderEntity;
import se.spline.query.neo4j.folder.FolderQueryRepository;
import se.spline.query.neo4j.repository.RepositoryEntity;
import se.spline.query.neo4j.repository.RepositoryQueryRepository;

import java.util.Collections;

@Component
public class RepositoryToFolderRelationRepository implements RelationshipRepository<Repository, String, Folder, String> {

    @Autowired
    private RepositoryQueryRepository repositoryQueryRepository;

    @Autowired
    private FolderQueryRepository folderQueryRepository;

    @Override
    public void setRelation(Repository source, String targetId, String fieldName) {

    }

    @Override
    public void setRelations(Repository source, Iterable<String> targetIds, String fieldName) {

    }

    @Override
    public void addRelations(Repository source, Iterable<String> targetIds, String fieldName) {

    }

    @Override
    public void removeRelations(Repository source, Iterable<String> targetIds, String fieldName) {

    }

    @Override
    public Folder findOneTarget(String sourceId, String fieldName, QueryParams queryParams) {
        final RepositoryEntity repositoryEntity = repositoryQueryRepository.findByRepositoryId(RepositoryId.builder().identifier(sourceId).build().getIdentifier());
        if(repositoryEntity == null || repositoryEntity.getRootFolder() == null ) {
            return null;
        }
        final FolderEntity folderEntity = folderQueryRepository.findByFolderId(repositoryEntity.getRootFolder().getFolderId());
        if(folderEntity == null) {
            return null;
        }
        return Folder.builder().id(folderEntity.getFolderId()).build();
    }

    @Override
    public Iterable<Folder> findManyTargets(String sourceId, String fieldName, QueryParams queryParams) {
        return Collections.singletonList(findOneTarget(sourceId, fieldName, queryParams));
    }
}
