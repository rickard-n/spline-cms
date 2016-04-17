package se.spline.query.neo4j.folder;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.folder.event.FolderCreatedEvent;
import se.spline.api.folder.event.FolderDeletedEvent;
import se.spline.api.folder.event.ParametersAddedToFolderEvent;
import se.spline.api.folder.parameter.FolderParameter;
import se.spline.utils.MapMerger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class FolderListener {
	private final Logger logger = LoggerFactory.getLogger(FolderListener.class);
	@Autowired
    private FolderQueryRepository folderRepository;

    @Autowired
    public FolderListener(FolderQueryRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @EventHandler
    public void handleFolderCreatedEvent(FolderCreatedEvent event, @Timestamp DateTime timeStamp) {
        final FolderEntity parentId = event.getParentId() != null ? folderRepository.findByFolderId(event.getParentId().getIdentifier()) : null;
        final FolderEntity folderEntity = FolderEntity.builder()
            .folderId(event.getId().getIdentifier())
            .name(event.getName())
            .created(timeStamp.toDate())
            .updated(timeStamp.toDate())
            .parent(parentId)
            .build();
        logger.debug("Save new folder to repository {}", folderEntity);
        folderRepository.save(folderEntity);
    }

	@EventHandler
	public void handleParametersAddedToFolderEvent(ParametersAddedToFolderEvent event) {
		final FolderEntity folderEntity = folderRepository.findByFolderId(event.getId().getIdentifier());
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

        folderRepository.delete(folderRepository.findByFolderId(event.getFolderId().getIdentifier()));
	}


}
