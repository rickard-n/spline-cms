package se.spline.query.folder;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.folder.FolderCreatedEvent;
import se.spline.api.folder.FolderMovedEvent;

@Component
public class FolderListener {

	private FolderQueryRepository companyRepository;

	@EventHandler
	public void handleFolderCreatedEvent(FolderCreatedEvent event) {
		FolderEntry folderEntry = new FolderEntry();
		folderEntry.setId(event.getFolderIdentifier().toString());
		folderEntry.setName(event.getName());
		if(folderEntry.getParentId() != null) {
			folderEntry.setParentId(event.getParentId().toString());
		}
		companyRepository.save(folderEntry);
	}

	@EventHandler
	public void handleFolderMovedEvent(FolderMovedEvent event) {
		final FolderEntry folderEntry = companyRepository.findOne(event.getFolderId().toString());
		folderEntry.setParentId(event.getNewParentId().toString());
		companyRepository.save(folderEntry);
	}

	@Autowired
	public void setCompanyRepository(FolderQueryRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
}
