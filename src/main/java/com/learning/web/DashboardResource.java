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
import com.learning.service.BranchService;
import com.learning.service.CourService;
import com.learning.service.ExamService;
import com.learning.service.GroupService;
import com.learning.service.LevelService;
import com.learning.service.ModuleAffectedService;
import com.learning.service.NoteExamService;
import com.learning.service.ProgressionCourService;
import com.learning.service.ProgressionModuleService;
import com.learning.service.UserService;

@RestController
@RequestMapping("/dashboard")
public class DashboardResource {

	private static Logger LOGGER = LogManager.getLogger("DashboardResource");
	@Autowired
	private ModuleAffectedService moduleService;

	@Autowired
	private ExamService examService;

	@Autowired
	private CourService courService;

	@Autowired
	private UserService userService;

	@Autowired
	private NoteExamService noteExamService;
	@Autowired
	private ProgressionModuleService progressionModuleService;
	@Autowired
	private ProgressionCourService progressionCourService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private LevelService levelService;

	@Autowired
	private GroupService groupService;

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
	public ResponseEntity<?> getAverageGoodAndBadGrades(@PathVariable Long idTeacher, @PathVariable Long idGroup,
			@PathVariable Long idModule) {
		try {
			return new ResponseEntity<>(noteExamService.getAverageGoodAndBadGrades(idTeacher, idGroup, idModule),
					HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/branch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getAverageSuccessStudent/{idTeacher}/{idGroup}/{idModule}")
	public ResponseEntity<?> getAverageSuccessStudent(@PathVariable Long idTeacher, @PathVariable Long idGroup,
			@PathVariable Long idModule) {
		try {
			return new ResponseEntity<>(progressionModuleService.getAverageSuccessStudent(idTeacher, idGroup, idModule),
					HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/branch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/countModuleByStudent/{idStudent}")
	public ResponseEntity<?> countModuleByStudent(@PathVariable Long idStudent) {
		try {
			return new ResponseEntity<>(progressionModuleService.countModuleByStudent(idStudent), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/branch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/countCourseByStudentAndModule/{idStudent}/{idModule}")
	public ResponseEntity<?> countCourseByStudentAndModule(@PathVariable Long idStudent, @PathVariable Long idModule) {
		try {
			return new ResponseEntity<>(progressionCourService.countCourseByStudentAndModule(idStudent, idModule),
					HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/branch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/countExamByStudentAndModule/{idStudent}/{idModule}/{type}")
	public ResponseEntity<?> countExamByStudentAndModule(@PathVariable Long idStudent, @PathVariable Long idModule,
			@PathVariable String type) {
		try {
			return new ResponseEntity<>(noteExamService.countExamByStudentAndModuleAndType(idStudent, idModule, type),
					HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/branch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/countUseryOrganizationAndLevelAndBranchAndGroup/{idOrg}/{idLevel}/{idBranch}/{idGroup}/{role}")
	public ResponseEntity<?> countUserByOrganizationAndLevelAndBranch(@PathVariable Long idOrg,
			@PathVariable Long idLevel, @PathVariable Long idBranch, @PathVariable Long idGroup,
			@PathVariable String role) {
		try {
			return new ResponseEntity<>(
					userService.countUserByOrganizationAndLevelAndBranch(idOrg, idLevel, idBranch, idGroup, role),
					HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/countUseryOrganizationAndLevelAndBranchAndGroup" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/countModuleyOrganizationAndLevelAndBranchAndGroup/{idOrg}/{idLevel}/{idBranch}/{idGroup}")
	public ResponseEntity<?> countModuleyOrganizationAndLevelAndBranch(@PathVariable Long idOrg,
			@PathVariable Long idLevel, @PathVariable Long idBranch, @PathVariable Long idGroup) {
		try {
			return new ResponseEntity<>(
					moduleService.countByOrganizationAndLevelAndBranch(idOrg, idLevel, idBranch, idGroup),
					HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/countModuleyOrganizationAndLevelAndBranchAndGroup" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("countBranchByOrganization/{idOrg}")
	public ResponseEntity<?> countBranchByOrganization(@PathVariable Long idOrg) {
		try {
			return new ResponseEntity<>(branchService.countByOrganization(idOrg), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/countBranchByOrganization" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@GetMapping("countOnlineUserByOrganization/{idOrg}")
	public ResponseEntity<?> countOnlineUserByOrganization(@PathVariable Long idOrg) {
		try {
			return new ResponseEntity<>(userService.countOnlineUserByOrganization(idOrg), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/countBranchByOrganization" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("countLevelByOrganization/{idOrg}")
	public ResponseEntity<?> countLevelByOrganization(@PathVariable Long idOrg) {
		try {
			return new ResponseEntity<>(levelService.countByOrganization(idOrg), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/countLevelByOrganization" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("countGroupByOrganizationAndLevelAndBranch/{idOrg}/{idLevel}/{idBranch}")
	public ResponseEntity<?> countGroupByOrganizationAndLevelAndBranch(@PathVariable Long idOrg,
			@PathVariable Long idLevel, @PathVariable Long idBranch) {
		try {
			return new ResponseEntity<>(groupService.countByOrganizationAndLevelAndBranch(idOrg, idLevel, idBranch), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/countGroupByOrganizationAndLevelAndBranch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/getAverageSuccessStudentByOrg/{idOrg}/{idLevel}/{idBranch}/{idGroup}")
	public ResponseEntity<?> getAverageSuccessStudentByOrg(@PathVariable Long idOrg, @PathVariable Long idLevel,
			@PathVariable Long idBranch,@PathVariable Long idGroup) {
		
		try {
			return new ResponseEntity<>(
					progressionModuleService.getAverageSuccessStudentByOrg(idOrg, idLevel, idBranch, idGroup),
					HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/branch" + ConstantBase.CRUD_REST_FIND_BY_ID + " : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
