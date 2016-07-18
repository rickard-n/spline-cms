package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.queryParams.params.FilterParams;
import io.katharsis.repository.ResourceRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.neo4j.ogm.cypher.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Component;
import se.spline.api.folder.FolderId;
import se.spline.api.folder.command.CreateFolderCommand;
import se.spline.api.folder.command.DeleteFolderCommand;
import se.spline.api.model.Folder;
import se.spline.api.repository.builder.FolderFactory;
import se.spline.query.neo4j.folder.FolderEntity;
import se.spline.query.neo4j.folder.FolderQueryRepository;

import java.util.Collections;

@Component
public class FolderResourceRepository implements ResourceRepository<Folder, String> {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private FolderQueryRepository folderQueryRepository;

    @Autowired
    private Neo4jOperations template;

    @Override
    public Folder findOne(String id, QueryParams queryParams) {
        return FolderFactory.from(folderQueryRepository.findByFolderId(id));
    }

    @Override
    public Iterable<Folder> findAll(QueryParams queryParams) {
        final FilterParams filterParams = queryParams.getFilters().getParams().getOrDefault("folder", new FilterParams(Collections.emptyMap()));
        final Filters filters = new Filters();
        filterParams.getParams().entrySet()
            .forEach(entry -> filters.add(entry.getKey(), entry.getValue().iterator().next()));
        if(filters.isEmpty()) {
            return FolderFactory.from(folderQueryRepository.findAll());
        }
        return FolderFactory.from(template.loadAllByProperties(FolderEntity.class, filters));
    }

    @Override
    public Iterable<Folder> findAll(Iterable<String> ids, QueryParams queryParams) {
        return FolderFactory.from(folderQueryRepository.findAllByFolderId(ids));
    }

    @Override
    public void delete(String id) {
        final DeleteFolderCommand deleteFolderCommand = new DeleteFolderCommand(FolderId.from(id));
        commandGateway.send(deleteFolderCommand);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Folder save(Folder entity) {
        if(entity.getId() == null) {
            return sendCreateFolderCommand(entity);
        } else {
            return sendUpdateFolderCommand(entity);
        }

    }

    private Folder sendUpdateFolderCommand(Folder entity) {
        return null;
    }

    private Folder sendCreateFolderCommand(Folder entity) {
        final CreateFolderCommand createFolderCommand = new CreateFolderCommand(
            new FolderId()
            , entity.getName()
            , FolderId.from(entity.getParent().getId()));
        return commandGateway.sendAndWait(createFolderCommand);
    }

}
