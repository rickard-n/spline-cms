package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.ResourceRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import se.spline.api.model.Folder;
import se.spline.api.model.Repository;
import se.spline.api.repository.command.CreateRepositoryCommand;
import se.spline.api.repository.command.DeleteRepositoryCommand;
import se.spline.api.repository.command.UpdateMetaDataForRepositoryCommand;
import se.spline.query.neo4j.repository.RepositoryEntity;
import se.spline.query.neo4j.repository.RepositoryQueryRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class RepositoryResourceRepository implements ResourceRepository<Repository, String> {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private RepositoryQueryRepository repositoryQueryRepository;

    @Autowired
    private RepositoryToFolderRelationRepository repositoryToFolderRelationRepository;

    @Override
    public Repository findOne(String id, QueryParams queryParams) {
        final RepositoryEntity entity = repositoryQueryRepository.findByRepositoryId(id);
        return buildRepositoryFromEntity(entity);
    }

    @Override
    public Iterable<Repository> findAll(QueryParams queryParams) {
        final Iterable<RepositoryEntity> entities = repositoryQueryRepository.findAll();
        return buildRepositoryListFromEntities(entities);
    }

    @Override
    public Iterable<Repository> findAll(Iterable<String> ids, QueryParams queryParams) {
        final Iterable<RepositoryEntity> entities = repositoryQueryRepository.findAllByRepositoryId(ids);
        return buildRepositoryListFromEntities(entities);
    }

    @Override
    public void delete(String id) {
        commandGateway.sendAndWait(new DeleteRepositoryCommand(RepositoryId.builder().identifier(id).build()));
    }

    @Override
    public Repository save(Repository entity) {
        final RepositoryMetaData metaData = RepositoryMetaData.builder()
            .name(entity.getName())
            .description(entity.getDescription())
            .build();
        if(StringUtils.isEmpty(entity.getId())) {
            return commandGateway.sendAndWait(new CreateRepositoryCommand(new RepositoryId(), metaData));
        } else {
            return commandGateway.sendAndWait(
                new UpdateMetaDataForRepositoryCommand(buildRepositoryIdFromStringIdentifier(entity.getId()), metaData)
            );
        }
    }

    private List<Repository> buildRepositoryListFromEntities(Iterable<RepositoryEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
            .map(this::buildRepositoryFromEntity)
            .collect(Collectors.toList());
    }

    private RepositoryId buildRepositoryIdFromStringIdentifier(String identifier) {
        return RepositoryId.builder().identifier(identifier).build();
    }

    private Repository buildRepositoryFromEntity(RepositoryEntity entity) {
        final Folder folder = repositoryToFolderRelationRepository.findOneTarget(entity.getRepositoryId(), null, null);
        return Repository.builder()
            .id(entity.getRepositoryId())
            .name(entity.getName())
            .description(entity.getDescription())
            .rootFolder(folder)
            .build();
    }
}
