package com.learning.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.UserDTO;
import com.learning.model.base.ConstantBase;
import com.learning.model.base.ConstantSecurity;
import com.learning.model.base.Demande;
import com.learning.security.CookieUtil;
import com.learning.security.SecurityConstants;
import com.learning.service.UserService;

@RestController
@RequestMapping("user")
public class UserResource {

	private static Logger LOGGER = LogManager.getLogger("UserResource");
	@Autowired
	UserService userService;

	@PostMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public ResponseEntity<?> findByCriteres(@RequestBody Demande<UserDTO> demande) {
		try {
			return new ResponseEntity<>(userService.findByCriteres(demande), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/user" + ConstantBase.CRUD_REST_FIND_BY_CRITERE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID + "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/user" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("find-by-organization/{id}/{idUser}")
	public ResponseEntity<?> findByOrganization(@PathVariable Long id, @PathVariable Long idUser) {
		try {
			return new ResponseEntity<>(userService.findAllByOrganisationWithoutUser(id, idUser), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/user" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(ConstantBase.CRUD_REST_DELETE + "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			userService.deleteById(id);
			return new ResponseEntity<>(SecurityConstants.convertObjectToJson(ConstantBase.DONE), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/user" + ConstantBase.CRUD_REST_DELETE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("permitAll()")
	@PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public ResponseEntity<?> save(@RequestBody UserDTO userDTO) {
		try {

			return new ResponseEntity<>(userService.saveU(userDTO), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/user" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(ConstantBase.CRUD_REST_FIND_ALL + "-professor-by-orga/{idOrg}")
	public ResponseEntity<?> findAll_Professor(@PathVariable Long idOrg) {
		try {

			return new ResponseEntity<>(userService.findAllProfessorByOrganisation(idOrg), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("notifications/{id}")
	public ResponseEntity<?> getNotifications(@PathVariable Long id) {
		try {

			return new ResponseEntity<>(userService.getNotificatonsById(id), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("findbyNameContaining/{name}/{examId}")
	public ResponseEntity<?> getNotifications(@PathVariable("name") String name, @PathVariable("examId") Long examId) {
		try {

			return new ResponseEntity<>(userService.findByNameContainingByExam(name, examId), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/cour" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("logout")
	public ResponseEntity<HttpStatus> logOut(HttpServletResponse httpServletResponse) {
		UserDetails userDetail = userService.getUserPrincipal();
		userService.logout(userDetail.getUsername());
		CookieUtil.clear(httpServletResponse, ConstantSecurity.COOKIE_NAME);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
