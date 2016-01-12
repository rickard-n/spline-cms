package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.model.Folder;
import se.spline.api.model.Repository;
import se.spline.query.folder.FolderEntity;
import se.spline.query.folder.FolderQueryRepository;
import se.spline.query.repository.RepositoryEntity;
import se.spline.query.repository.RepositoryQueryRepository;

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
        final RepositoryEntity repositoryEntity = repositoryQueryRepository.findOne(RepositoryId.builder().identifier(sourceId).build());
        if(repositoryEntity == null) {
            return null;
        }
        final FolderEntity folderEntity = folderQueryRepository.findOne(repositoryEntity.getRootFolder());
        if(folderEntity == null) {
            return null;
        }
        return Folder.builder().id(folderEntity.getId().getIdentifier()).build();
    }

    @Override
    public Iterable<Folder> findManyTargets(String sourceId, String fieldName, QueryParams queryParams) {
        return Collections.singletonList(findOneTarget(sourceId, fieldName, queryParams));
    }
}
