package se.spline.query.folder;

import org.springframework.data.repository.PagingAndSortingRepository;
import se.spline.api.folder.FolderId;

public interface FolderQueryRepository extends PagingAndSortingRepository<FolderEntity, FolderId> {


}
