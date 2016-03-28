package se.spline.api.folder.event;

import lombok.Data;
import lombok.NonNull;
import se.spline.api.folder.FolderId;

@Data
public class FolderCreatedEvent {
    @NonNull
    private final FolderId id;
    @NonNull
	private final String name;

	private final FolderId parentId;
}
