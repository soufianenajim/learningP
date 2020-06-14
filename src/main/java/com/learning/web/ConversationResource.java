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

import com.learning.dto.ConversationDTO;
import com.learning.model.base.ConstantBase;
import com.learning.model.base.Demande;
import com.learning.security.SecurityConstants;
import com.learning.service.ConversationService;
import com.learning.service.MessageService;

@RestController
@RequestMapping("/conversation")
public class ConversationResource {

	private static Logger LOGGER = LogManager.getLogger("ConversationResource");
	@Autowired
	private ConversationService conversationService;
	@Autowired
	private MessageService messageService;

	@GetMapping("countNotReadMsgs/{idUser1}/{idUser2}")
	public ResponseEntity<?> countNotReadMsg(@PathVariable Long idUser1, @PathVariable Long idUser2) {
		try {
			return new ResponseEntity<>(conversationService.countNotReadMsgByUser1AndUser2(idUser1, idUser2),
					HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/conversation" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("find_Messages/{idUser1}/{idUser2}/{page}")
	public ResponseEntity<?> findMessages(@PathVariable Long idUser1, @PathVariable Long idUser2,
			@PathVariable int page) {
		try {
			return new ResponseEntity<>(messageService.findByUser1AndUser2(idUser1, idUser2,page), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/conversation" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("read/{idUser1}/{idUser2}")
	public ResponseEntity<?> read(@PathVariable Long idUser1, @PathVariable Long idUser2) {
		try {
			conversationService.read(idUser1, idUser2);
			return new ResponseEntity<>(SecurityConstants.convertObjectToJson(ConstantBase.DONE), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/conversation" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
