package se.spline.query.repository;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.repository.event.RepositoryCreatedEvent;
import se.spline.api.repository.event.RepositoryRootFolderChangedEvent;

@Component
public class RepositoryListener {

    private final RepositoryQueryRepository repositoryQueryRepository;

    @Autowired
    public RepositoryListener(RepositoryQueryRepository repositoryQueryRepository) {
        this.repositoryQueryRepository = repositoryQueryRepository;
    }

    @EventHandler
    public void handleRepositoryCreatedEvent(RepositoryCreatedEvent event) {
        final RepositoryEntity entity = RepositoryEntity.builder().id(event.getId()).name(event.getMetaData().getName()).build();
        repositoryQueryRepository.save(entity);
    }

    @EventHandler
    public void handleRepositoryRootFolderUpdate(RepositoryRootFolderChangedEvent event) {
        final RepositoryEntity repositoryEntity = repositoryQueryRepository.findOne(event.getRepositoryId());
        repositoryEntity.setRootFolder(event.getFolderId());
        repositoryQueryRepository.save(repositoryEntity);
    }
}
