package se.spline.api.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se.spline.api.model.Repository;
import se.spline.api.repository.RepositoryId;
import se.spline.api.repository.RepositoryMetaData;
import se.spline.api.request.repository.RepositoryConverter;
import se.spline.api.request.repository.RepositoryRequest;
import se.spline.query.repository.RepositoryEntity;
import se.spline.query.repository.RepositoryQueryRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("repository")
@Api(value = "repository", description = "Repository resource")
public class RepositoryResource extends AbstractResource {

    @Autowired
    private RepositoryQueryRepository repositoryQueryRepository;

    @Autowired
    private RepositoryConverter repositoryConverter;

    @ApiOperation(value = "Create a new repository.",
        response = Repository.class, httpMethod = "POST")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Repository> createRepository(@Valid @RequestBody RepositoryRequest repositoryRequest) {
        final RepositoryMetaData repository = repositoryConverter.convert(repositoryRequest);
        apiService.addRepository(repository);
        return new ResponseEntity<>(Repository.builder()
            .name(repository.getName())
            .build(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all repositories.",
        response = Repository.class, responseContainer = "List", httpMethod = "GET")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Repository> getRepositories() {
        final Iterable<RepositoryEntity> repositoryEntities = repositoryQueryRepository.findAll();
        return StreamSupport.stream(repositoryEntities.spliterator(), false)
            .map(repositoryEntity -> Repository.builder()
                .id(repositoryEntity.getId().getIdentifier())
                .name(repositoryEntity.getName())
                .folderId(repositoryEntity.getRootFolder() != null ? repositoryEntity.getRootFolder().getIdentifier() : null)
                .build())
            .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get a repository.",
        response = Repository.class, httpMethod = "GET")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Repository getRepository(@PathVariable("id") String id) {
        final RepositoryEntity repositoryEntity = repositoryQueryRepository.findOne(RepositoryId.builder().identifier(id).build());
        return Repository.builder()
            .id(repositoryEntity.getId().getIdentifier())
            .name(repositoryEntity.getName())
            .folderId(repositoryEntity.getRootFolder() != null ? repositoryEntity.getRootFolder().getIdentifier() : null)
            .build();
    }
}
