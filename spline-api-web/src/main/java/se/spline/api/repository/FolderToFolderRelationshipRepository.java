package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.folder.FolderId;
import se.spline.api.model.Folder;
import se.spline.api.repository.builder.FolderRelationFactory;
import se.spline.query.neo4j.folder.FolderEntity;
import se.spline.query.neo4j.folder.FolderQueryRepository;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class FolderToFolderRelationshipRepository implements RelationshipRepository<Folder, String, Folder, String> {

    @Autowired
    private FolderQueryRepository folderQueryRepository;

    @Override
    public void setRelation(Folder source, String targetId, String fieldName) {

    }

    @Override
    public void setRelations(Folder source, Iterable<String> targetIds, String fieldName) {

    }

    @Override
    public void addRelations(Folder source, Iterable<String> targetIds, String fieldName) {

    }

    @Override
    public void removeRelations(Folder source, Iterable<String> targetIds, String fieldName) {

    }

    @Override
    public Folder findOneTarget(String sourceId, String fieldName, QueryParams queryParams) {


        final FolderEntity folderEntity = folderQueryRepository.findByFolderId(FolderId.builder().identifier(sourceId).build().getIdentifier());
        if("parent".equals(fieldName)) {
            return FolderRelationFactory.from(folderEntity.getParent());
        }

        return null;
    }

    @Override
    public Iterable<Folder> findManyTargets(String sourceId, String fieldName, QueryParams queryParams) {
        if("children".equals(fieldName)) {
            final Iterable<FolderEntity> folderEntity = folderQueryRepository.findChildrenForFolderWithId(sourceId);
            return StreamSupport.stream(folderEntity.spliterator(), false)
                .map(FolderRelationFactory::from)
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
