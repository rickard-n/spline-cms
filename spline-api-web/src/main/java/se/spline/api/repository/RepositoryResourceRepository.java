package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.ResourceRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.model.Folder;
import se.spline.api.model.Repository;
import se.spline.api.repository.command.CreateRepositoryCommand;
import se.spline.query.repository.RepositoryEntity;
import se.spline.query.repository.RepositoryQueryRepository;

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
    public Repository findOne(String s, QueryParams queryParams) {
        final RepositoryEntity entity = repositoryQueryRepository.findOne(buildRepositoryIdFromStringIdentifier(s));
        return buildRepositoryFromEntity(entity);
    }

    @Override
    public Iterable<Repository> findAll(QueryParams queryParams) {
        final Iterable<RepositoryEntity> entities = repositoryQueryRepository.findAll();
        return buildRepositoryListFromEntities(entities);
    }

    @Override
    public Iterable<Repository> findAll(Iterable<String> strings, QueryParams queryParams) {
        final List<RepositoryId> ids = StreamSupport.stream(strings.spliterator(), false)
            .map(this::buildRepositoryIdFromStringIdentifier)
            .collect(Collectors.toList());
        final Iterable<RepositoryEntity> entities = repositoryQueryRepository.findAll(ids);
        return buildRepositoryListFromEntities(entities);
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public Repository save(Repository entity) {
        final RepositoryMetaData metaData = RepositoryMetaData.builder().name(entity.getName()).build();
        return commandGateway.sendAndWait(new CreateRepositoryCommand(new RepositoryId(), metaData));
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
        final Folder folder = repositoryToFolderRelationRepository.findOneTarget(entity.getId().getIdentifier(), null, null);
        return Repository.builder()
            .id(entity.getId().getIdentifier())
            .name(entity.getName())
            .rootFolder(folder)
            .build();
    }
}
