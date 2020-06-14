package com.learning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dao.ConversationRepository;
import com.learning.dto.ConversationDTO;
import com.learning.model.Conversation;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ConversationService;

@Service
public class ConversationServiceImpl implements ConversationService {

	@Autowired
	private ConversationRepository conversationRepository;
	@Override
	public ConversationDTO save(ConversationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConversationDTO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Conversation model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<ConversationDTO> findByCriteres(Demande<ConversationDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conversation convertDTOtoModel(ConversationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConversationDTO convertModelToDTO(Conversation model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<ConversationDTO> convertToListDTO(PartialList<Conversation> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConversationDTO> convertEntitiesToDtos(List<Conversation> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conversation> convertDtosToEntities(List<ConversationDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conversation findByUser1AndUser2(Long idUser1, Long idUser2) {
		
		return conversationRepository.findByUser1AndUser2(idUser1, idUser2);
	}

	@Override
	public Conversation saveConversation(Conversation conversation) {
	return 	conversationRepository.save(conversation);
		
	}

	@Override
	public int countNotReadMsgByUser1AndUser2(Long idUser1, Long idUser2) {
		Integer count =conversationRepository.countNotReadMsgByUser1AndUser2(idUser1, idUser2);
		return count!=null?count:0;
	}

	@Override
	public void read(Long idUser1, Long idUser2) {
		conversationRepository.read(idUser1, idUser2);
		
	}


}
