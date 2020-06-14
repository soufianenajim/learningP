package com.learning.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.learning.dto.MessageDTO;
import com.learning.service.MessageService;

@Controller
public class WebSocketController {

	@Autowired
	private MessageService messageService;

	private final SimpMessagingTemplate template;

	@Autowired
	WebSocketController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/send/message/{fromUserId}/{toUserId}")
	public void onReceivedMesage(String message, @DestinationVariable Long fromUserId,
			@DestinationVariable Long toUserId) {
		messageService.saveMessage(fromUserId, toUserId,message);
		this.template.convertAndSend("/chat/" + fromUserId + "/" + toUserId, new MessageDTO(message, LocalDateTime.now()));
	}
	

}