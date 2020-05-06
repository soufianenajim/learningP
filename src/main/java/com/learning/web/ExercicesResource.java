package com.learning.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.ExercicesDTO;
import com.learning.model.base.ConstantBase;
import com.learning.model.base.Demande;
import com.learning.security.SecurityConstants;
import com.learning.service.ExercicesService;

@RestController
@RequestMapping("/exercices")
public class ExercicesResource {

	private static Logger LOGGER = LogManager.getLogger("ExercicesResource");
	@Autowired
	ExercicesService exercicesService;

	@PostMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public ResponseEntity<?> findByCriteres(@RequestBody Demande<ExercicesDTO> demande) {
		try {
			return new ResponseEntity<>(exercicesService.findByCriteres(demande), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/exercices" + ConstantBase.CRUD_REST_FIND_BY_CRITERE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID + "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(exercicesService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/exercices" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(ConstantBase.CRUD_REST_DELETE + "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			exercicesService.deleteById(id);
			return new ResponseEntity<>(SecurityConstants.convertObjectToJson(ConstantBase.DONE), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/exercices" + ConstantBase.CRUD_REST_DELETE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public ResponseEntity<?> save(@RequestBody ExercicesDTO exercicesDTO) {
		try {

			ExercicesDTO exercices = exercicesService.save(exercicesDTO);
			if (exercices != null) {
				return new ResponseEntity<>(exercices, HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/exercices" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("find-by-module-type/{id}/{type}")
	public ResponseEntity<?> findByModule(@PathVariable Long id,@PathVariable String type) {
		try {
			return new ResponseEntity<>(exercicesService.findByModuleAndType(id,type), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/exercices" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@GetMapping("find-by-cour-type/{id}/{type}")
	public ResponseEntity<?> findByCourAndType(@PathVariable Long id,@PathVariable String type) {
		try {
			return new ResponseEntity<>(exercicesService.findByCourAndType(id,type), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/exercices" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	@GetMapping("find-by-question/{id}")
	public ResponseEntity<?> findByQuestion(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(exercicesService.findByQuestion(id), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/exercices" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
