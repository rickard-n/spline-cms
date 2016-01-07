package se.spline.entity.folder;

import org.axonframework.domain.DomainEventMessage;
import se.spline.api.folder.FolderId;
import se.spline.api.folder.event.RootFolderCreatedEvent;
import se.spline.api.repository.RepositoryData;

public class RootFolder extends Folder {

    public RootFolder(FolderId folderId, RepositoryData repository) {
        apply(new RootFolderCreatedEvent(folderId, repository));
    }

    @Override
    protected void handle(DomainEventMessage eventMessage) {
        if (eventMessage.getPayloadType().equals(RootFolderCreatedEvent.class)) {
            RootFolderCreatedEvent event = (RootFolderCreatedEvent) eventMessage.getPayload();
            this.id = event.getFolderId();
        }
    }
}
