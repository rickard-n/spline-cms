package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.ResourceRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.folder.FolderId;
import se.spline.api.folder.command.CreateFolderCommand;
import se.spline.api.folder.command.DeleteFolderCommand;
import se.spline.api.model.Folder;
import se.spline.api.repository.builder.FolderRelationBuilder;
import se.spline.query.neo4j.folder.FolderEntity;
import se.spline.query.neo4j.folder.FolderQueryRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class FolderResourceRepository implements ResourceRepository<Folder, String> {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private FolderQueryRepository folderQueryRepository;


    @Override
    public Folder findOne(String s, QueryParams queryParams) {
        final FolderEntity entity = folderQueryRepository.findByFolderId(buildFolderIdFromStringIdentifier(s).getIdentifier());
        return buildFolderFromEntity(entity);
    }

    @Override
    public Iterable<Folder> findAll(QueryParams queryParams) {
        final Iterable<FolderEntity> entities = folderQueryRepository.findAll();
        return buildFolderListFromEntities(entities);
    }

    @Override
    public Iterable<Folder> findAll(Iterable<String> strings, QueryParams queryParams) {
        final List<String> ids = StreamSupport.stream(strings.spliterator(), false)
            .map(this::buildFolderIdFromStringIdentifier)
            .map(FolderId::getIdentifier)
            .collect(Collectors.toList());
        final Iterable<FolderEntity> entities = folderQueryRepository.findAllByFolderId(ids);
        return buildFolderListFromEntities(entities);
    }

    @Override
    public void delete(String s) {
        final DeleteFolderCommand deleteFolderCommand = new DeleteFolderCommand(buildFolderIdFromStringIdentifier(s));
        commandGateway.send(deleteFolderCommand);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Folder save(Folder entity) {
        final CreateFolderCommand createFolderCommand = new CreateFolderCommand(
            entity.getId() != null ? buildFolderIdFromStringIdentifier(entity.getId()) : new FolderId()
            , entity.getName()
            , buildFolderIdFromStringIdentifier(entity.getParent().getId()));
        return commandGateway.sendAndWait(createFolderCommand);
    }

    private List<Folder> buildFolderListFromEntities(Iterable<FolderEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
            .map(this::buildFolderFromEntity)
            .collect(Collectors.toList());
    }

    private Folder buildFolderFromEntity(FolderEntity entity) {
        final List<Folder> children = getChildren(entity);
        final Folder.FolderBuilder builder = Folder.builder()
            .id(entity.getFolderId())
            .created(entity.getCreated())
            .updated(entity.getUpdated())
            .version(entity.getVersion())
            .properties(entity.getProperties());
        if(entity.getParentId() != null) {
            builder.parent(FolderRelationBuilder.builder().id(entity.getParentId()).build());
        }
        return builder
            .children(children)
            .build();
    }

    private List<Folder> getChildren(FolderEntity entity) {
        if(entity.getChildren() == null) {
            return Collections.emptyList();
        }
        return entity.getChildren().stream()
            .map(id -> FolderRelationBuilder.builder().id(FolderId.builder().identifier(id.getFolderId()).build()).build())
            .collect(Collectors.toList());
    }

    private FolderId buildFolderIdFromStringIdentifier(String identifier) {
        return FolderId.builder().identifier(identifier).build();
    }
}
