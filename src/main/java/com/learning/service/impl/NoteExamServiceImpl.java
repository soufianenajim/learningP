package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.NoteExamRepository;
import com.learning.dto.NoteExamDTO;
import com.learning.model.NoteExam;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ExamService;
import com.learning.service.NoteExamService;
import com.learning.service.UserService;

@Service
public class NoteExamServiceImpl implements NoteExamService {

	@Autowired
	private NoteExamRepository noteExamRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ExamService examService;

	// save or update
	@Override
	public NoteExamDTO save(NoteExamDTO noteExamDTO) {
		NoteExam noteExam = convertDTOtoModel(noteExamDTO);
		noteExam = noteExamRepository.save(noteExam);
		return convertModelToDTO(noteExam);
	}

	@Override
	public NoteExamDTO findById(long idOut) {
		Optional<NoteExam> optional = noteExamRepository.findById(idOut);

		if (optional.isPresent()) {
			NoteExam noteExamFromDb = optional.get();
			return convertModelToDTO(noteExamFromDb);
		}
		return null;
	}

	@Override
	public void delete(NoteExam noteExam) {
		noteExamRepository.delete(noteExam);
	}

	@Override
	public PartialList<NoteExamDTO> findByCriteres(Demande<NoteExamDTO> demande) {

		NoteExamDTO noteExam = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<NoteExam> pageNoteExam = noteExamRepository.findByUserAndExam(noteExam.getUser().getId(),
				noteExam.getExam().getId(), PageRequest.of(page, size));

		List<NoteExamDTO> list = convertEntitiesToDtos(pageNoteExam.getContent());
		Long totalElement = pageNoteExam.getTotalElements();

		return new PartialList<NoteExamDTO>(totalElement, list);
	}

	@Override
	public NoteExam convertDTOtoModel(NoteExamDTO noteExamDTO) {
		NoteExam noteExam = new NoteExam();
		noteExam.setId(noteExamDTO.getId());
		noteExam.setScore(noteExamDTO.getScore());

		if (noteExamDTO.getUser() != null) {
			noteExam.setUser(userService.convertDTOtoModel(noteExamDTO.getUser()));
		}

		if (noteExamDTO.getExam() != null) {
			noteExam.setExam(examService.convertDTOtoModel(noteExamDTO.getExam()));
		}
		return noteExam;
	}

	@Override
	public NoteExamDTO convertModelToDTO(NoteExam noteExam) {
		NoteExamDTO noteExamDTO = new NoteExamDTO();
		noteExamDTO.setId(noteExam.getId());
		noteExamDTO.setScore(noteExam.getScore());
		User user = noteExam.getUser();
		if (user != null) {
			noteExamDTO.setUser(userService.convertModelToDTO(noteExam.getUser()));

		}

		noteExamDTO.setCreatedAt(noteExam.getCreatedAt());
		noteExamDTO.setUpdatedAt(noteExam.getUpdatedAt());
		return noteExamDTO;
	}

	@Override
	public PartialList<NoteExamDTO> convertToListDTO(PartialList<NoteExam> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		noteExamRepository.deleteById(id);

	}

	@Override
	public List<NoteExamDTO> convertEntitiesToDtos(List<NoteExam> noteExams) {
		// basic methode
		List<NoteExamDTO> list = new ArrayList<NoteExamDTO>();
		for (NoteExam noteExam : noteExams) {
			list.add(convertModelToDTO(noteExam));
		}
		return list;
	}

	@Override
	public List<NoteExam> convertDtosToEntities(List<NoteExamDTO> noteExamsDTO) {
		List<NoteExam> list = new ArrayList<NoteExam>();
		for (NoteExamDTO noteExamDTO : noteExamsDTO) {
			list.add(convertDTOtoModel(noteExamDTO));
		}
		return list;
	}

}
