package com.learning.service;

import java.util.List;

import com.learning.dto.ProgressionCourDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Cour;
import com.learning.model.ProgressionCour;

public interface ProgressionCourService extends CrudService<ProgressionCour, ProgressionCourDTO> {
	
	void saveByCourAndStudents(Cour cour,List<UserDTO> students);
	
}
