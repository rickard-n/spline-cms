package se.spline.api.resources;

import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
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
import se.spline.query.folder.FolderEntry;
import se.spline.query.folder.FolderQueryRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(value = "folders", description = "CRUD for folders.")
@RestController
public class FolderResource extends AbstractResource {

	@Autowired
	FolderQueryRepository folderQueryRepository;

	@Autowired
	FolderConverter folderConverter;

	@Autowired
	PropertiesConverter propertiesConverter;

	@ApiOperation(value = "Create a folder", notes = "Creates a new folder.")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<FolderEntry> createFolder(@Valid @RequestBody FolderRequest folderRequest) {
		final Folder folder = folderConverter.convert(folderRequest);
		apiService.addFolder(folder);
		final FolderEntry folderEntry = new FolderEntry();
		folderEntry.setId(folder.getId().toString());
		return new ResponseEntity<>(folderEntry, HttpStatus.OK);
	}

	@ApiOperation(value = "Get all folder", notes = "Get information about all folder.")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<FolderEntry> getFolders() {
		final Iterable<FolderEntry> folderEntries = folderQueryRepository.findAll();
		return Lists.newArrayList(folderEntries);
	}


	@ApiOperation(value = "Get a folder", notes = "Get information about a folder.")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public FolderEntry getFolder(@PathVariable String id) {
		return folderQueryRepository.findOne(id);
	}

	@ApiOperation(value = "Add properties to a folder.", notes = "Add properties to folder.")
	@RequestMapping(value = "/{id}/properties", method = RequestMethod.POST)
	@ResponseBody
	public FolderEntry updateFolder(@PathVariable String id, @RequestBody PropertiesRequest properties) {
		final Map<String, String> map = propertiesConverter.convert(properties);
		final FolderEntry folder = folderQueryRepository.findOne(id);
		apiService.addProperties(folder, map);
		return folder;
	}

	@ApiOperation(value = "Delete folder.", notes = "Delete a folder.")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteFolder(@PathVariable String id) {
		final FolderEntry folder = folderQueryRepository.findOne(id);
		apiService.deleteFolder(folder);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}


}
