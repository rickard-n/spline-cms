package se.spline.query.folder;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.folder.event.FolderCreatedEvent;
import se.spline.api.folder.event.FolderDeletedEvent;
import se.spline.api.folder.event.ParametersAddedToFolderEvent;
import se.spline.api.folder.event.RootFolderCreatedEvent;
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

    @Autowired
    public void FolderListener(FolderQueryRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @EventHandler
    public void handleFolderCreatedEvent(RootFolderCreatedEvent event) {
        final FolderEntity.FolderEntityBuilder builder = FolderEntity.builder();
        builder.id(event.getFolderId())
            .name(event.getRepository().getName());
        final FolderEntity folderEntity = builder.build();
        logger.debug("Save new root folder to repository {}", folderEntity);
        folderRepository.save(folderEntity);
    }

	@EventHandler
	public void handleFolderCreatedEvent(FolderCreatedEvent event) {
        final FolderEntity.FolderEntityBuilder builder = FolderEntity.builder();
        builder.id(event.getFolderIdentifier())
            .name(event.getName());
        if(event.getParentId() != null) {
            final FolderEntity parentId = folderRepository.findOne(event.getParentId());
            parentId.add(event.getFolderIdentifier());
            logger.debug("Add child {} to folder {}", event.getFolderIdentifier(), parentId);
            folderRepository.save(parentId);
            builder.parentId(event.getParentId());
		}
        final FolderEntity folderEntity = builder.build();
        logger.debug("Save new folder to repository {}", folderEntity);
		folderRepository.save(folderEntity);
	}

	@EventHandler
	public void handleParametersAddedToFolderEvent(ParametersAddedToFolderEvent event) {
		final FolderEntity folderEntity = folderRepository.findOne(event.getId());
		final Map<String, String> originalProperties = folderEntity.getProperties();
		final List<FolderParameter<?>> parameters = event.getParameters();
		final Map<String, String> map = new HashMap<>(parameters.size());
		for(FolderParameter folderParameter : parameters) {
			map.put(folderParameter.getName(), (String) folderParameter.getValue());
		}

		final Map<String, String> mergedParameters = MapMerger.mergeMaps(Stream.of(originalProperties, map));
		folderEntity.setProperties(mergedParameters);
		folderRepository.save(folderEntity);
	}

	@EventHandler
	public void handleFolderDeletedEvent(FolderDeletedEvent event) {
		folderRepository.delete(event.getFolderId());
	}


}
