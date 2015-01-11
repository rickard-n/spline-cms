package se.spline.api.service;

import se.spline.api.domain.folder.Folder;
import se.spline.query.folder.FolderEntry;

import java.util.Map;

public interface ApiService {


	void addFolder(Folder folder);

	void addProperties(FolderEntry folder, Map<String, String> properties);

	void deleteFolder(FolderEntry folder);
}
