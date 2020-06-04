package com.learning.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.learning.dto.CourDTO;
import com.learning.model.base.ConstantBase;
import com.learning.model.base.Demande;
import com.learning.security.SecurityConstants;
import com.learning.service.CourService;
import com.learning.service.FilesStorageService;
import com.learning.util.FileInfo;

@RestController
@RequestMapping("/cour")
public class CourResource {

	private static Logger LOGGER = LogManager.getLogger("CourResource");
	@Autowired
	CourService courService;
	@Autowired
	FilesStorageService storageService;

	@PostMapping("/save-with-file")
	public ResponseEntity<?> uploadFile(@RequestPart("files") List<MultipartFile> files,
			@RequestPart("cour") CourDTO courDTO) {
		String message = "";
		try {
			CourDTO cour = courService.saveWithFile(courDTO, files);
			if (cour != null) {
				return new ResponseEntity<>(cour, HttpStatus.CREATED);
			}
			// storageService.save(file);

//	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
//	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<FileInfo>> getListFiles() {
		List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(CourResource.class, "getFile", path.getFileName().toString()).build().toString();

			return new FileInfo(filename, url);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("load/{courId}/{name}")
	public ResponseEntity<?> getFile(@PathVariable Long courId, @PathVariable String name)  {
		try {
			File reader = courService.load(courId, name);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(reader));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + reader.getName() + "\"").body(resource);	
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_FIND_BY_CRITERE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PostMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public ResponseEntity<?> findByCriteres(@RequestBody Demande<CourDTO> demande) {
		try {
			return new ResponseEntity<>(courService.findByCriteres(demande), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_FIND_BY_CRITERE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID + "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(courService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(ConstantBase.CRUD_REST_DELETE + "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			courService.deleteById(id);
			return new ResponseEntity<>(SecurityConstants.convertObjectToJson(ConstantBase.DONE), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_DELETE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("deleteAttachment/{id}/{fileName}")
	public ResponseEntity<?> deleteByAttachment(@PathVariable Long id, @PathVariable String fileName) {
		try {
			courService.deleteAttachment(id, fileName);
			return new ResponseEntity<>(SecurityConstants.convertObjectToJson(ConstantBase.DONE), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_DELETE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public ResponseEntity<?> save(@RequestBody CourDTO courDTO) {
		try {

			CourDTO cour = courService.save(courDTO);
			if (cour != null) {
				return new ResponseEntity<>(cour, HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(ConstantBase.CRUD_REST_FIND_ALL)
	public ResponseEntity<?> findAll() {
		try {

			return new ResponseEntity<>(courService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("find-by-module" + "/{id}")
	public ResponseEntity<?> findByModule(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(courService.findByModule(id), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("find-by-module-and-not-launched" + "/{id}")
	public ResponseEntity<?> findByModuleAndNotLaunched(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(courService.findByModuleAndNotLaunched(id), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("launch" + "/{id}")
	public ResponseEntity<?> launch(@PathVariable Long id) {
		try {
			courService.launch(id);
			return new ResponseEntity<>(SecurityConstants.convertObjectToJson(ConstantBase.DONE), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
