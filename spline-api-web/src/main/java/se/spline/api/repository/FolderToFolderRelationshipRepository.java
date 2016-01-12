package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.folder.FolderId;
import se.spline.api.model.Folder;
import se.spline.api.repository.builder.FolderRelationBuilder;
import se.spline.query.folder.FolderEntity;
import se.spline.query.folder.FolderQueryRepository;

import java.util.Collections;
import java.util.stream.Collectors;

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
        final FolderEntity folderEntity = folderQueryRepository.findOne(FolderId.builder().identifier(sourceId).build());
        if("parent".equals(fieldName)) {
            return FolderRelationBuilder.builder().id(folderEntity.getParentId()).build();
        }

        return null;
    }

    @Override
    public Iterable<Folder> findManyTargets(String sourceId, String fieldName, QueryParams queryParams) {
        final FolderEntity folderEntity = folderQueryRepository.findOne(FolderId.builder().identifier(sourceId).build());
        if("children".equals(fieldName)) {
            return folderEntity.getChildren().stream()
                .map(id -> FolderRelationBuilder.builder().id(id).build())
                .collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }
}
