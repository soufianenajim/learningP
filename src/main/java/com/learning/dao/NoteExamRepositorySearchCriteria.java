package com.learning.dao;
import java.util.List;

import com.learning.dto.NoteExamDTO;
import com.learning.model.NoteExam;
import com.learning.model.base.Demande;

public interface NoteExamRepositorySearchCriteria {
	
	public List<NoteExam> findByCriteres(Demande<NoteExamDTO> demande);
	
	public Long countByCriteres(Demande<NoteExamDTO> demande);


}