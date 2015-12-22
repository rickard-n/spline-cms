package se.spline.api.resources;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se.spline.api.domain.folder.Folder;
import se.spline.api.request.folder.FolderConverter;
import se.spline.api.request.folder.FolderRequest;
import se.spline.api.request.folder.PropertiesConverter;
import se.spline.api.request.folder.PropertiesRequest;
import se.spline.query.folder.FolderEntity;
import se.spline.query.folder.FolderQueryRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

//@Api(value = "folders", description = "CRUD for folders.")
@RestController
@RequestMapping("folder")
public class FolderResource extends AbstractResource {

	@Autowired
	FolderQueryRepository folderQueryRepository;

	@Autowired
	FolderConverter folderConverter;

	@Autowired
	PropertiesConverter propertiesConverter;

	//@ApiOperation(value = "Create a folder", notes = "Creates a new folder.")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<FolderEntity> createFolder(@Valid @RequestBody FolderRequest folderRequest) {
		final Folder folder = folderConverter.convert(folderRequest);
		apiService.addFolder(folder);
		final FolderEntity folderEntity = new FolderEntity();
		folderEntity.setId(folder.getId().toString());
		return new ResponseEntity<>(folderEntity, HttpStatus.OK);
	}

	//@ApiOperation(value = "Get all folder", notes = "Get information about all folder.")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<FolderEntity> getFolders() {
		final Iterable<FolderEntity> folderEntries = folderQueryRepository.findAll();
		return Lists.newArrayList(folderEntries);
	}


	//@ApiOperation(value = "Get a folder", notes = "Get information about a folder.")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public FolderEntity getFolder(@PathVariable String id) {
		return folderQueryRepository.findOne(id);
	}

	//@ApiOperation(value = "Add properties to a folder.", notes = "Add properties to folder.")
	@RequestMapping(value = "/{id}/properties", method = RequestMethod.POST)
	@ResponseBody
	public FolderEntity updateFolder(@PathVariable String id, @RequestBody PropertiesRequest properties) {
		final Map<String, String> map = propertiesConverter.convert(properties);
		final FolderEntity folder = folderQueryRepository.findOne(id);
		apiService.addProperties(folder, map);
		return folder;
	}

	//@ApiOperation(value = "Delete folder.", notes = "Delete a folder.")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteFolder(@PathVariable String id) {
		final FolderEntity folder = folderQueryRepository.findOne(id);
		apiService.deleteFolder(folder);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}


}
