package com.learning.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.model.base.ConstantBase;
import com.learning.service.CourService;
import com.learning.service.ExamService;
import com.learning.service.ModuleService;
import com.learning.service.NoteExamService;
import com.learning.service.UserService;

@RestController
@RequestMapping("/dashboard")
public class DashboardResource {

	private static Logger LOGGER = LogManager.getLogger("DashboardResource");
	@Autowired
	private ModuleService moduleService;

	@Autowired
	private ExamService examService;

	@Autowired
	private CourService courService;

	@Autowired
	private UserService userService;

	@Autowired
	private NoteExamService noteExamService;

	@GetMapping("/countModuleByTeacherAndGroupe/{idTeacher}/{idGroup}")
	public ResponseEntity<?> countModuleByTeacherAndGroupe(@PathVariable Long idTeacher, @PathVariable Long idGroup) {
		try {
			return new ResponseEntity<>(moduleService.countModuleByTeacherAndGroup(idTeacher, idGroup), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/branch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/countExamByTeacherAndGroupe/{idTeacher}/{idGroup}/{type}")
	public ResponseEntity<?> countExamByTeacherAndGroupe(@PathVariable Long idTeacher, @PathVariable Long idGroup,
			@PathVariable String type) {
		try {
			return new ResponseEntity<>(examService.countExamByTeacherAndGroupAndType(idTeacher, idGroup, type),
					HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/branch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/countCourByTeacherAndGroupe/{idTeacher}/{idGroup}")
	public ResponseEntity<?> countCourByTeacherAndGroupe(@PathVariable Long idTeacher, @PathVariable Long idGroup) {
		try {
			return new ResponseEntity<>(courService.countCourByTeacherAndGroup(idTeacher, idGroup), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/branch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/countStudentByTeacherAndGroupe/{idTeacher}/{idGroup}")
	public ResponseEntity<?> countStudentByTeacherAndGroupe(@PathVariable Long idTeacher, @PathVariable Long idGroup) {
		try {
			return new ResponseEntity<>(userService.countStudentByTeacherAndGroup(idTeacher, idGroup), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/branch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getAverageGoodAndBadGrades/{idTeacher}/{idGroup}/{idModule}")
	public ResponseEntity<?> getAverageGoodAndBadGrades(@PathVariable Long idTeacher, @PathVariable Long idGroup,@PathVariable Long idModule) {
		try {
			return new ResponseEntity<>(noteExamService.getAverageGoodAndBadGrades(idTeacher, idGroup,idModule), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/branch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
