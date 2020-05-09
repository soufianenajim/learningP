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
	
	boolean existingExercice( Long idCour,TypeEnum type);

	boolean existingExerciceById(Long id, Long idCour,TypeEnum type);
	
	 ExercicesDTO convertModelToDTOWithoutQuestion(Exercices exercices);
	 
	 boolean existingExercice(ExercicesDTO exercicesDTO);
}
