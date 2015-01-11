package se.spline.api.service;

import se.spline.api.domain.Folder.Folder;

public interface ApiService {


	void addFolder(Folder folder);

	void moveFolder(String folderId, String parentId);
}
