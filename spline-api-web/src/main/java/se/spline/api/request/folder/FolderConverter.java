package se.spline.api.request.folder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import se.spline.api.domain.folder.Folder;
import se.spline.api.folder.FolderId;

@Component
public class FolderConverter implements Converter<FolderRequest, Folder> {
	@Override
	public Folder convert(FolderRequest source) {
		final FolderId folderId = StringUtils.isEmpty(source.getId()) ? new FolderId() : new FolderId(source.getId());
		final FolderId parentId = StringUtils.isEmpty(source.getParentId()) ? null : new FolderId(source.getParentId());
		return new Folder(folderId, source.getName(), parentId);
	}
}
