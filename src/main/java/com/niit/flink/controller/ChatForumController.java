package com.niit.flink.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.flink.model.Message;
import com.niit.flink.model.OutputMessage;

@Controller
public class ChatForumController {
     @MessageMapping("/chat_forum")
     @SendTo("/topic/message")
	public OutputMessage sendMessage(Message message){
    	 return new OutputMessage(message,new Date());
	}
	
	
}
