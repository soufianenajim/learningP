package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.ExamRepository;
import com.learning.dto.ExamDTO;
import com.learning.model.Exam;
import com.learning.model.Module;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ExamService;
import com.learning.service.ModuleService;
import com.learning.service.QuestionService;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private QuestionService questionService;

	@Override
	public ExamDTO save(ExamDTO examDTO) {
		Exam exam = convertDTOtoModel(examDTO);
		exam = examRepository.save(exam);
		if (examDTO.getQuestions() != null) {
			questionService.saveQuestionsByExam(examDTO.getQuestions(), exam);
		}
		return convertModelToDTO(exam);
	}

	@Override
	public ExamDTO findById(long idOut) {
		Optional<Exam> optional = examRepository.findById(idOut);

		if (optional.isPresent()) {
			Exam examFromDb = optional.get();
			return convertModelToDTO(examFromDb);
		}
		return null;
	}

	@Override
	public void delete(Exam exam) {
		examRepository.delete(exam);

	}

	@Override
	public PartialList<ExamDTO> findByCriteres(Demande<ExamDTO> demande) {

		ExamDTO exam = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Exam> pageExam = null;
		String name = exam.getName();
		Long idModule = exam.getModule() != null ? exam.getModule().getId() : null;

		pageExam = idModule != null ? examRepository.findByNameAndModule(name, idModule, PageRequest.of(page, size))
				: examRepository.findByName(name, PageRequest.of(page, size));

		List<ExamDTO> list = convertEntitiesToDtos(pageExam.getContent());
		Long totalElement = pageExam.getTotalElements();
		return new PartialList<ExamDTO>(totalElement, list);

	}

	@Override
	public Exam convertDTOtoModel(ExamDTO examDTO) {
		Exam exam = new Exam();
		exam.setId(examDTO.getId());
		exam.setName(examDTO.getName());

		if (examDTO.getModule() != null) {
			exam.setModule(moduleService.convertDTOtoModel(examDTO.getModule()));
		}
		return exam;
	}

	@Override
	public ExamDTO convertModelToDTO(Exam exam) {
		ExamDTO examDTO = new ExamDTO();
		examDTO.setId(exam.getId());
		examDTO.setName(exam.getName());
		Module module = exam.getModule();
		if (module != null) {
			examDTO.setModule(moduleService.convertModelToDTO(exam.getModule()));
		}
		examDTO.setCreatedAt(exam.getCreatedAt());
		examDTO.setUpdatedAt(exam.getUpdatedAt());
		return examDTO;
	}

	@Override
	public PartialList<ExamDTO> convertToListDTO(PartialList<Exam> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		examRepository.deleteById(id);
		questionService.detachExam(id);

	}

	@Override
	public List<ExamDTO> convertEntitiesToDtos(List<Exam> exams) {
		// basic methode
		List<ExamDTO> list = new ArrayList<ExamDTO>();
		for (Exam exam : exams) {
			list.add(convertModelToDTO(exam));
		}
		return list;
	}

	@Override
	public List<Exam> convertDtosToEntities(List<ExamDTO> examsDTO) {
		List<Exam> list = new ArrayList<Exam>();
		for (ExamDTO examDTO : examsDTO) {
			list.add(convertDTOtoModel(examDTO));
		}
		return list;
	}

	@Override
	public void deleteByModule(Long idModule) {
		examRepository.deleteByModule(idModule);
		
	}

}
