package com.learning.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.learning.dto.CourDTO;
import com.learning.model.Cour;

public interface CourService extends CrudService<Cour, CourDTO> {
	Cour findEnitityById(Long id);

	List<CourDTO> findAll();

	void deleteByModule(Long idModule);

	Cour convertDTOtoModelWithOutModule(CourDTO courDTO);

	CourDTO convertModelToDTOWithOutModule(final Cour cour);

	List<CourDTO> convertEntitiesToDtosWithOutModule(List<Cour> list);

	List<Cour> convertDtosToEntitiesWithOutModule(List<CourDTO> list);

	List<CourDTO> findByModule(Long idModule);

	List<CourDTO> findByModuleAndNotLaunched(Long idModule);

	List<CourDTO> findByModuleAndLaunched(Long idModule, boolean isLaunched);

	void launch(Long idCour);

	boolean existingCour(String name, Long idModule);

	boolean existingCourById(Long id, String name, Long idModule);

	Long countCourByTeacherAndGroup(Long idTeacher, Long idGroup);

	CourDTO saveWithFile(CourDTO cour, List<MultipartFile> files);

	File load(Long id, String nameFile);
	
	void deleteAttachment(Long idCour,String name);
}
