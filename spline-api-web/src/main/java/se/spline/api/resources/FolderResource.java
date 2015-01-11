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
import se.spline.query.folder.FolderEntry;
import se.spline.query.folder.FolderQueryRepository;

import javax.validation.Valid;
import java.util.List;

@Api(value = "folders", description = "CRUD for folders.")
@RestController
public class FolderResource extends AbstractResource {

	@Autowired
	FolderQueryRepository folderQueryRepository;

	@Autowired
	FolderConverter folderConverter;

	@ApiOperation(value = "Create a folder", notes = "Creates a new folder.")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<FolderEntry> createFolder(@Valid @RequestBody FolderRequest folderRequest) {
		final Folder folder = folderConverter.convert(folderRequest);
		apiService.addFolder(folder);
		final FolderEntry folderEntry = new FolderEntry();
		folderEntry.setId(folder.getId().toString());
		return new ResponseEntity<>(folderEntry, HttpStatus.OK);
	}

	@ApiOperation(value = "Create a folder", notes = "Creates a new folder.")
	@RequestMapping(value = "/{folderId}/move/{parentId}", method = RequestMethod.GET)
	public String moveFolder(@PathVariable("folderId") String folderId, @PathVariable("parentId") String parentId) {
		apiService.moveFolder(folderId, parentId);
		return "redirect:/" + folderId;
	}

	@ApiOperation(value = "Get a folder", notes = "Get information about a folder.")
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
}
