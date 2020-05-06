package com.learning.service;

import java.util.List;

import com.learning.dto.ExercicesDTO;
import com.learning.model.Exercices;
import com.learning.model.TypeEnum;

public interface ExercicesService extends CrudService<Exercices, ExercicesDTO> {
	ExercicesDTO findByCourAndType(Long courId,String type);
	
	ExercicesDTO findByQuestion(Long questionId);
	
	List<ExercicesDTO> findByModuleAndType(Long idModule,String type);
	
	ExercicesDTO convertModelToDTOWithQuestion(Exercices quiz);
	
	boolean existingExam(String name,TypeEnum type, Long idModule);

	boolean existingExamById(Long id, String name,TypeEnum type, Long idModule);
	
	
}
