package se.spline.query.folder;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.folder.FolderCreatedEvent;
import se.spline.api.folder.FolderDeletedEvent;
import se.spline.api.folder.ParametersAddedToFolderEvent;
import se.spline.api.folder.parameter.FolderParameter;
import se.spline.utils.MapMerger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
	public void handleParametersAddedToFolderEvent(ParametersAddedToFolderEvent event) {
		final FolderEntry folderEntry = folderRepository.findOne(event.getId().toString());
		final Map<String, String> originalProperties = folderEntry.getProperties();
		final List<FolderParameter<?>> parameters = event.getParameters();
		final Map<String, String> map = new HashMap<>(parameters.size());
		for(FolderParameter folderParameter : parameters) {
			map.put(folderParameter.getName(), (String) folderParameter.getValue());
		}

		final Map<String, String> mergedParameters = MapMerger.mergeMaps(Stream.of(originalProperties, map));
		folderEntry.setProperties(mergedParameters);
		folderRepository.save(folderEntry);
	}

	@EventHandler
	public void handleFolderDeletedEvent(FolderDeletedEvent event) {
		folderRepository.delete(event.getFolderId().toString());
	}

	@Autowired
	public void setFolderRepository(FolderQueryRepository folderRepository) {
		this.folderRepository = folderRepository;
	}
}
