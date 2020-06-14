package com.learning.service;

import java.util.List;

import com.learning.dto.CourDTO;
import com.learning.dto.ProgressionCourDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Cour;
import com.learning.model.ProgressionCour;
import com.learning.model.User;

public interface ProgressionCourService extends CrudService<ProgressionCour, ProgressionCourDTO> {

	void saveByCourAndStudents(Cour cour, List<UserDTO> students);

	void saveByStudentAndCours(User student, List<CourDTO> cours);

	List<Double> listOfProgressionByModuleAndStudent(Long idModule, Long idStudent);

	Long countCourseByStudentAndModule(Long idStudent, Long idModule);
}
