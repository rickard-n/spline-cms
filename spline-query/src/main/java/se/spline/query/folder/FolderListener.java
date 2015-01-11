package se.spline.query.folder;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.folder.FolderCreatedEvent;
import se.spline.api.folder.FolderMovedEvent;

@Component
public class FolderListener {
	private final Logger logger = LoggerFactory.getLogger(FolderListener.class);
	private FolderQueryRepository folderRepository;

	@EventHandler
	public void handleFolderCreatedEvent(FolderCreatedEvent event) {
		FolderEntry folderEntry = new FolderEntry();
		folderEntry.setId(event.getFolderIdentifier().toString());
		folderEntry.setName(event.getName());
		if(event.getParentId() != null) {
			folderEntry.setParentId(event.getParentId().toString());
		}
		logger.debug("Save new folder to repository {}", folderEntry);
		folderRepository.save(folderEntry);
	}

	@EventHandler
	public void handleFolderMovedEvent(FolderMovedEvent event) {
		final FolderEntry folderEntry = folderRepository.findOne(event.getFolderId().toString());
		folderEntry.setParentId(event.getNewParentId().toString());
		folderRepository.save(folderEntry);
	}

	@Autowired
	public void setFolderRepository(FolderQueryRepository folderRepository) {
		this.folderRepository = folderRepository;
	}
}
