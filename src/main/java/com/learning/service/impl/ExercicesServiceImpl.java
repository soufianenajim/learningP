package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.learning.dao.ExercicesRepository;
import com.learning.dao.QuestionRepository;
import com.learning.dto.ExercicesDTO;
import com.learning.dto.QuestionDTO;
import com.learning.model.Cour;
import com.learning.model.Exercices;
import com.learning.model.Question;
import com.learning.model.TypeEnum;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.CourService;
import com.learning.service.ExercicesService;
import com.learning.service.QuestionService;

@Service
public class ExercicesServiceImpl implements ExercicesService {

	@Autowired
	private ExercicesRepository exercicesRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuestionService questionService;
	@Autowired
	private CourService courService;

	@Override
	public ExercicesDTO save(ExercicesDTO exercicesDTO) {
		TypeEnum type = TypeEnum.valueOf(exercicesDTO.getType());
		if (exercicesDTO.getId() != null) {
			if (!existingExerciceById(exercicesDTO.getId(), exercicesDTO.getCour().getId(), type))
				return null;
		} else if (!existingExercice(exercicesDTO.getCour().getId(), type))
			return null;
		Exercices exercices = convertDTOtoModel(exercicesDTO);
		exercices = exercicesRepository.save(exercices);
		if (exercicesDTO.getQuestions() != null) {
			questionService.saveQuestionsByExercices(exercicesDTO.getQuestions(), exercices);
		}
		return convertModelToDTO(exercices);
	}

	@Override
	public ExercicesDTO findById(long idOut) {
		Optional<Exercices> optional = exercicesRepository.findById(idOut);

		if (optional.isPresent()) {
			Exercices exercicesFromDb = optional.get();
			return convertModelToDTO(exercicesFromDb);
		}
		return null;
	}

	@Override
	public void delete(Exercices exercices) {
		exercicesRepository.delete(exercices);
	}

	@Override
	public PartialList<ExercicesDTO> findByCriteres(Demande<ExercicesDTO> demande) {

		ExercicesDTO exercices = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Exercices> pageExercices = null;
		String name = exercices.getName();
		String type = exercices.getType();

		pageExercices = type != null
				? exercicesRepository.findByNameAndType(name, TypeEnum.valueOf(type), PageRequest.of(page, size))
				: exercicesRepository.findByName(name, PageRequest.of(page, size));

		List<ExercicesDTO> list = convertEntitiesToDtos(pageExercices.getContent());
		Long totalElement = pageExercices.getTotalElements();

		return new PartialList<ExercicesDTO>(totalElement, list);
	}

	@Override
	public Exercices convertDTOtoModel(ExercicesDTO exercicesDTO) {
		Exercices exercices = new Exercices();
		exercices.setId(exercicesDTO.getId());
		exercices.setName(exercicesDTO.getName());
		exercices.setStartDateTime(exercicesDTO.getStartDateTime());
		exercices.setEndDateTime(exercicesDTO.getEndDateTime());
		exercices.setScale(exercicesDTO.getScale());

		if (exercicesDTO.getType() != null && !StringUtils.isEmpty(exercicesDTO.getType())) {
			exercices.setType(TypeEnum.valueOf(exercicesDTO.getType()));
		}
		if (exercicesDTO.getCour() != null) {
			exercices.setCour(courService.convertDTOtoModel(exercicesDTO.getCour()));
		}

		return exercices;
	}

	@Override
	public ExercicesDTO convertModelToDTO(Exercices exercices) {
		ExercicesDTO exercicesDTO = new ExercicesDTO();
		exercicesDTO.setId(exercices.getId());
		exercicesDTO.setName(exercices.getName());
		exercicesDTO.setStartDateTime(exercices.getStartDateTime());
		exercicesDTO.setEndDateTime(exercices.getEndDateTime());
		exercicesDTO.setScale(exercices.getScale());
		Cour cour = exercices.getCour();
		TypeEnum type = exercices.getType();
		List<Question> questions = exercices.getQuestions();
		if (cour != null) {
			exercicesDTO.setCour(courService.convertModelToDTO(cour));
		}
		if (type != null) {
			exercicesDTO.setType(type.toString());
		}
		if (questions != null) {
			exercicesDTO.setQuestions(questionService.convertEntitiesToDtos(questions));
		}
		exercicesDTO.setCreatedAt(exercices.getCreatedAt());
		exercicesDTO.setUpdatedAt(exercices.getUpdatedAt());
		return exercicesDTO;
	}

	@Override
	public ExercicesDTO convertModelToDTOWithoutQuestion(Exercices exercices) {
		ExercicesDTO exercicesDTO = new ExercicesDTO();
		exercicesDTO.setId(exercices.getId());
		exercicesDTO.setName(exercices.getName());
		exercicesDTO.setStartDateTime(exercices.getStartDateTime());
		exercicesDTO.setEndDateTime(exercices.getEndDateTime());
		exercicesDTO.setScale(exercices.getScale());
		Cour cour = exercices.getCour();
		TypeEnum type = exercices.getType();

		if (cour != null) {
			exercicesDTO.setCour(courService.convertModelToDTO(cour));
		}
		if (type != null) {
			exercicesDTO.setType(type.toString());
		}

		exercicesDTO.setCreatedAt(exercices.getCreatedAt());
		exercicesDTO.setUpdatedAt(exercices.getUpdatedAt());
		return exercicesDTO;
	}

	@Override
	public PartialList<ExercicesDTO> convertToListDTO(PartialList<Exercices> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		Boolean existed = exercicesRepository.existsById(id);
		if (existed) {
			exercicesRepository.deleteById(id);
		}
	}

	@Override
	public List<ExercicesDTO> convertEntitiesToDtos(List<Exercices> exercicess) {
		// basic methode
		List<ExercicesDTO> list = new ArrayList<ExercicesDTO>();
		for (Exercices exercices : exercicess) {
			list.add(convertModelToDTO(exercices));
		}
		return list;
	}

	@Override
	public List<Exercices> convertDtosToEntities(List<ExercicesDTO> exercicessDTO) {
		List<Exercices> list = new ArrayList<Exercices>();
		for (ExercicesDTO exercicesDTO : exercicessDTO) {
			list.add(convertDTOtoModel(exercicesDTO));
		}
		return list;
	}

	@Override
	public ExercicesDTO findByCourAndType(Long courId, String type) {
		Exercices exercices = exercicesRepository.findByCourAndType(courId, TypeEnum.valueOf(type));
		return exercices != null ? convertModelToDTO(exercices) : null;

	}

	@Override
	public List<ExercicesDTO> findByModuleAndType(Long idModule, String type) {

		return convertEntitiesToDtos(exercicesRepository.findByModuleAndType(idModule, TypeEnum.valueOf(type)));
	}

	@Override
	public ExercicesDTO convertModelToDTOWithQuestion(Exercices quiz) {

		ExercicesDTO quizDTO = convertModelToDTO(quiz);
		List<Question> questions = quiz.getQuestions();
		if (questions != null) {
			quizDTO.setQuestions(questionService.convertEntitiesToDtos(questions));
		}
		return quizDTO;
	}

	@Override
	public ExercicesDTO findByQuestion(Long questionId) {
		Optional<Question> question = questionRepository.findById(questionId);
		Exercices exercices = question.isPresent() ? question.get().getExercices() : null;
		return exercices != null ? convertModelToDTO(exercices) : null;
	}

	@Override
	public boolean existingExercice(Long idCour, TypeEnum type) {
		Exercices existExercice = exercicesRepository.findByCourAndType(idCour, type);
		return existExercice == null || existExercice.getId() == null;
	}

	@Override
	public boolean existingExerciceById(Long id, Long idCour, TypeEnum type) {
		Exercices existExercice = exercicesRepository.findByCourAndType(idCour, type);
		return existExercice == null || existExercice.getId().equals(id);
	}

	@Override
	public boolean existingExercice(ExercicesDTO exercicesDTO) {
		TypeEnum type = TypeEnum.valueOf(exercicesDTO.getType());
		return exercicesDTO.getId() != null
				? existingExerciceById(exercicesDTO.getId(), exercicesDTO.getCour().getId(), type)
				: existingExercice(exercicesDTO.getCour().getId(), type);

	}

}
