package com.learning.service;

import java.util.List;

import com.learning.dto.MessageDTO;
import com.learning.model.Message;

public interface MessageService extends CrudService<Message, MessageDTO> {

	void saveMessage(Long userFrom, Long userTo,String message);
	
	List<MessageDTO> 	findByUser1AndUser2(Long idUser1,Long idUser2,int page);
	
	

}
