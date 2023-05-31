package com.clone.daangnmarketserver.chatting.presentation;

import com.clone.daangnmarketserver.chatting.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  private final SimpMessagingTemplate simpMessagingTemplate;

  public MessageController(SimpMessagingTemplate simpMessagingTemplate) {
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  @MessageMapping("/message")
  public void chatting(Message message) {
    simpMessagingTemplate.convertAndSend("/message", message.getFrom() + " :: " + message.getText());
  }
}
