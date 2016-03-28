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
import se.spline.api.repository.builder.FolderRelationFactory;
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
    public Folder findOne(String id, QueryParams queryParams) {
        final FolderEntity entity = folderQueryRepository.findByFolderId(id);
        return buildFolderFromEntity(entity);
    }

    @Override
    public Iterable<Folder> findAll(QueryParams queryParams) {
        final Iterable<FolderEntity> entities = folderQueryRepository.findAll();
        return buildFolderListFromEntities(entities);
    }

    @Override
    public Iterable<Folder> findAll(Iterable<String> ids, QueryParams queryParams) {
        final Iterable<FolderEntity> entities = folderQueryRepository.findAllByFolderId(ids);
        return buildFolderListFromEntities(entities);
    }

    @Override
    public void delete(String id) {
        final DeleteFolderCommand deleteFolderCommand = new DeleteFolderCommand(FolderId.from(id));
        commandGateway.send(deleteFolderCommand);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Folder save(Folder entity) {
        final CreateFolderCommand createFolderCommand = new CreateFolderCommand(
            entity.getId() != null ? FolderId.from(entity.getId()) : new FolderId()
            , entity.getName()
            , FolderId.from(entity.getParent().getId()));
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
            .properties(entity.getProperties());
        if(entity.getParent() != null) {
            builder.parent(FolderRelationFactory.from(entity.getParent()));
        }
        return builder
            .children(children)
            .build();
    }

    private List<Folder> getChildren(FolderEntity entity) {
        //if(entity.getChildren() == null) {
            return Collections.emptyList();
        /*}
        return entity.getChildren().stream()
            .filter(child -> child != null)
            .map(FolderRelationFactory::from)
            .collect(Collectors.toList()); */
    }
}
