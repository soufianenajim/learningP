package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.NoteQuizRepository;
import com.learning.dto.NoteQuizDTO;
import com.learning.model.NoteQuiz;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.NoteQuizService;
import com.learning.service.QuizService;
import com.learning.service.UserService;

@Service
public class NoteQuizServiceImpl implements NoteQuizService {

	@Autowired
	private NoteQuizRepository noteQuizRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private QuizService quizService;

	// save or update
	@Override
	public NoteQuizDTO save(NoteQuizDTO noteQuizDTO) {
		NoteQuiz noteQuiz = convertDTOtoModel(noteQuizDTO);
		noteQuiz = noteQuizRepository.save(noteQuiz);
		return convertModelToDTO(noteQuiz);
	}

	@Override
	public NoteQuizDTO findById(long idOut) {
		Optional<NoteQuiz> optional = noteQuizRepository.findById(idOut);

		if (optional.isPresent()) {
			NoteQuiz noteQuizFromDb = optional.get();
			return convertModelToDTO(noteQuizFromDb);
		}
		return null;
	}

	@Override
	public void delete(NoteQuiz noteQuiz) {
		noteQuizRepository.delete(noteQuiz);
	}

	@Override
	public PartialList<NoteQuizDTO> findByCriteres(Demande<NoteQuizDTO> demande) {

		NoteQuizDTO noteQuiz = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<NoteQuiz> pageNoteQuiz = noteQuizRepository.findByScore(noteQuiz.getScore(), PageRequest.of(page, size));

		List<NoteQuizDTO> list = convertEntitiesToDtos(pageNoteQuiz.getContent());
		int totalElement = pageNoteQuiz.getNumberOfElements();

		return new PartialList<NoteQuizDTO>(totalElement, list);
	}

	@Override
	public NoteQuiz convertDTOtoModel(NoteQuizDTO noteQuizDTO) {
		NoteQuiz noteQuiz = new NoteQuiz();
		noteQuiz.setId(noteQuizDTO.getId());
		noteQuiz.setScore(noteQuizDTO.getScore());

		if (noteQuizDTO.getUser() != null) {
			noteQuiz.setUser(userService.convertDTOtoModel(noteQuizDTO.getUser()));
		}

		if (noteQuizDTO.getQuiz() != null) {
			noteQuiz.setQuiz(quizService.convertDTOtoModel(noteQuizDTO.getQuiz()));
		}
		return noteQuiz;
	}

	   
	   

	@Override
	public NoteQuizDTO convertModelToDTO(NoteQuiz noteQuiz) {
		NoteQuizDTO noteQuizDTO = new NoteQuizDTO();
		noteQuizDTO.setId(noteQuiz.getId());
		noteQuizDTO.setScore(noteQuiz.getScore());
		User user = noteQuiz.getUser();
		if (user != null) {
			noteQuizDTO.setUser(userService.convertModelToDTO(noteQuiz.getUser()));
			
		}
		
		noteQuizDTO.setCreatedAt(noteQuiz.getCreatedAt());
		noteQuizDTO.setUpdatedAt(noteQuiz.getUpdatedAt());
		return noteQuizDTO;
	}

	@Override
	public PartialList<NoteQuizDTO> convertToListDTO(PartialList<NoteQuiz> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		noteQuizRepository.deleteById(id);

	}

	@Override
	public List<NoteQuizDTO> convertEntitiesToDtos(List<NoteQuiz> noteQuizs) {
		// basic methode
		List<NoteQuizDTO> list = new ArrayList<NoteQuizDTO>();
		for (NoteQuiz noteQuiz : noteQuizs) {
			list.add(convertModelToDTO(noteQuiz));
		}
		return list;
	}

	@Override
	public List<NoteQuiz> convertDtosToEntities(List<NoteQuizDTO> noteQuizsDTO) {
		List<NoteQuiz> list = new ArrayList<NoteQuiz>();
		for (NoteQuizDTO noteQuizDTO : noteQuizsDTO) {
			list.add(convertDTOtoModel(noteQuizDTO));
		}
		return list;
	}

}
