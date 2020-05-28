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

import com.learning.dto.ModuleAffectedDTO;
import com.learning.model.base.ConstantBase;
import com.learning.model.base.Demande;
import com.learning.security.SecurityConstants;
import com.learning.service.ModuleAffectedService;

@RestController
@RequestMapping("/module-affected")

public class ModuleAffectedResource {

	private static Logger LOGGER = LogManager.getLogger("ModuleResource");
	@Autowired
	ModuleAffectedService moduleAffectedService;

	@PostMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public ResponseEntity<?> findByCriteres(@RequestBody Demande<ModuleAffectedDTO> demande) {
		try {
			return new ResponseEntity<>(moduleAffectedService.findByCriteres(demande), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/moduleAffected" + ConstantBase.CRUD_REST_FIND_BY_CRITERE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID + "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(moduleAffectedService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/moduleAffected" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(ConstantBase.CRUD_REST_DELETE + "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			moduleAffectedService.deleteById(id);
			return new ResponseEntity<>(SecurityConstants.convertObjectToJson(ConstantBase.DONE), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/moduleAffected" + ConstantBase.CRUD_REST_DELETE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public ResponseEntity<?> save(@RequestBody ModuleAffectedDTO moduleAffectedDTO) {
		try {

			ModuleAffectedDTO moduleAffected = moduleAffectedService.save(moduleAffectedDTO);
			if (moduleAffected != null) {
				return new ResponseEntity<>(moduleAffected, HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/moduleAffected" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(ConstantBase.CRUD_REST_FIND_ALL)
	public ResponseEntity<?> findAll() {
		try {

			return new ResponseEntity<>(moduleAffectedService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("find-by-group/{idGroup}")
	public ResponseEntity<?> findByLevelAndBranch(@PathVariable Long idGroup) {
		try {

			return new ResponseEntity<>(moduleAffectedService.findByGroup(idGroup), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("find-by-professor/{idProfessor}")
	public ResponseEntity<?> findByProfessor(@PathVariable Long idProfessor) {
		try {

			return new ResponseEntity<>(moduleAffectedService.findByProfessor(idProfessor), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("find-by-professor-group/{idProfessor}/{idGroup}")
	public ResponseEntity<?> findByProfessorAndGroup(@PathVariable Long idProfessor,@PathVariable Long idGroup) {
		try {

			return new ResponseEntity<>(moduleAffectedService.findByProfessorAndGroup(idProfessor, idGroup), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("calculate")
	public ResponseEntity<?> calculate(@RequestBody ModuleAffectedDTO moduleAffected) {
		try {
			moduleAffectedService.calculate(moduleAffected);
			return new ResponseEntity<>(SecurityConstants.convertObjectToJson(ConstantBase.DONE), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}
