package com.learning.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.learning.dao.MessageRepository;
import com.learning.dto.MessageDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Conversation;
import com.learning.model.Message;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ConversationService;
import com.learning.service.MessageService;
import com.learning.service.UserService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private UserService userService;
	@Autowired
	private ConversationService conversationService;
	
	@Autowired
	
	private MessageRepository messageRepository;

	@Override
	public MessageDTO save(MessageDTO messageDTO) {
		return null;
	}

	@Override
	public MessageDTO findById(long id) {
		return null;
	}

	@Override
	public void delete(Message model) {

	}

	@Override
	public void deleteById(Long id) {

	}

	@Override
	public PartialList<MessageDTO> findByCriteres(Demande<MessageDTO> demande) {
		return null;
	}

	@Override
	public Message convertDTOtoModel(MessageDTO messageDTO) {
		Message message = new Message();
		message.setId(messageDTO.getId());
		message.setMessage(messageDTO.getMessage());
		message.setTime(LocalDateTime.now());
		message.setUserFrom(userService.convertDTOtoModelWithOutRelation(messageDTO.getUserFrom()));

		return message;
	}

	@Override
	public MessageDTO convertModelToDTO(Message model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<MessageDTO> convertToListDTO(PartialList<Message> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageDTO> convertEntitiesToDtos(List<Message> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> convertDtosToEntities(List<MessageDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Async
	@Override
	public void saveMessage(Long idUserFrom, Long idUserTo,String messageBody) {
		Conversation conversation = conversationService.findByUser1AndUser2(idUserFrom, idUserTo);
		User userFrom = userService.findUserById(idUserFrom);
		User userTo = userService.findUserById(idUserTo);
		if (conversation != null) {
			conversation.setNotReadMessages(conversation.getNotReadMessages() + 1);
			conversationService.saveConversation(conversation);
		} else {
			conversation = new Conversation();
			conversation.setNotReadMessages(1);
			conversation.setUser1(userFrom);
			conversation.setUser2(userTo);
			conversation=conversationService.saveConversation(conversation);
		}
		
		Message message = new Message();
		message.setMessage(messageBody);
		message.setConversation(conversation);
		message.setTime(LocalDateTime.now());
		message.setUserFrom(userFrom);
		message.setUserTo(userTo);
		messageRepository.saveAndFlush(message);

	}

}
