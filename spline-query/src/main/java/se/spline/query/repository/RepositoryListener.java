package se.spline.query.repository;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.replay.ReplayAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.repository.event.RepositoryCreatedEvent;

@Component
public class RepositoryListener implements ReplayAware {

    private final RepositoryQueryRepository repositoryQueryRepository;

    @Autowired
    public RepositoryListener(RepositoryQueryRepository repositoryQueryRepository) {
        this.repositoryQueryRepository = repositoryQueryRepository;
    }

    @EventHandler
    public void handleRepositoryCreatedEvent(RepositoryCreatedEvent event) {
        final RepositoryEntity entity = RepositoryEntity.builder().id(event.getId()).name(event.getName()).build();
        repositoryQueryRepository.save(entity);
    }

    @Override
    public void beforeReplay() {

    }

    @Override
    public void afterReplay() {

    }

    @Override
    public void onReplayFailed(Throwable cause) {

    }
}
