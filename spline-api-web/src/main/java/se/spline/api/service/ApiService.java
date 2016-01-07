package se.spline.api.service;

import se.spline.api.domain.folder.Folder;
import se.spline.api.repository.RepositoryMetaData;
import se.spline.query.folder.FolderEntity;

import java.util.Map;

public interface ApiService {


	void addFolder(Folder folder);

	void addProperties(FolderEntity folder, Map<String, String> properties);

	void deleteFolder(FolderEntity folder);

    void addRepository(RepositoryMetaData repository);
}
