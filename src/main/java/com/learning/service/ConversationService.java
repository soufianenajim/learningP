package com.learning.service;

import com.learning.dto.ConversationDTO;
import com.learning.model.Conversation;

public interface ConversationService extends CrudService<Conversation, ConversationDTO> {

	Conversation findByUser1AndUser2(Long idUser1, Long idUser2);

	Conversation saveConversation(Conversation conversation);

	int countNotReadMsgByUser1AndUser2(Long idUser1, Long idUser2);

	void read(Long idUser1, Long idUser2);
}
