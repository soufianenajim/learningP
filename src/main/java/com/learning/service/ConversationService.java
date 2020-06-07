package com.learning.service;

import com.learning.dto.ConversationDTO;
import com.learning.dto.MessageDTO;
import com.learning.model.Conversation;
import com.learning.model.Message;

public interface ConversationService extends CrudService<Conversation, ConversationDTO> {

	Conversation findByUser1AndUser2(Long idUser1, Long idUser2);

	Conversation saveConversation(Conversation conversation);

}
