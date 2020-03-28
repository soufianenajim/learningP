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

import com.learning.dto.NoteExamDTO;
import com.learning.model.base.ConstantBase;
import com.learning.model.base.Demande;
import com.learning.security.SecurityConstants;
import com.learning.service.NoteExamService;

@RestController
@RequestMapping("/noteExam")
public class NoteExamResource {

	private static Logger LOGGER = LogManager.getLogger("NoteExamResource");
	@Autowired
	NoteExamService noteExamService;

	@PostMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public ResponseEntity<?> findByCriteres(Demande<NoteExamDTO> demande) {
		try {
			return new ResponseEntity<>(noteExamService.findByCriteres(demande), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/noteExam" + ConstantBase.CRUD_REST_FIND_BY_CRITERE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID + "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(noteExamService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/noteExam" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(ConstantBase.CRUD_REST_DELETE + "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			noteExamService.deleteById(id);
			return new ResponseEntity<>(SecurityConstants.convertObjectToJson(ConstantBase.DONE), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/noteExam" + ConstantBase.CRUD_REST_DELETE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public ResponseEntity<?> save(@RequestBody NoteExamDTO noteExamDTO) {
		try {

			return new ResponseEntity<>(noteExamService.save(noteExamDTO), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/noteExam" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
