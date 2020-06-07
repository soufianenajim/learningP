package com.learning.service;

import com.learning.dto.MessageDTO;
import com.learning.model.Message;

public interface MessageService extends CrudService<Message, MessageDTO> {

	void saveMessage(Long userFrom, Long userTo,String message);

}
