package com.niit.flink.appconfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan("com.niit.flink")
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

	public void  configureMessageBroker(MessageBrokerRegistry config){
		System.out.println("configure message broker");
		config.enableSimpleBroker("/topic","/queue");
		config.setApplicationDestinationPrefixes("/app");
		
	}
	
	
	
	public void registerStompEndpoints(StompEndpointRegistry resgistry) {
		System.out.println("stomp end points");
		resgistry.addEndpoint("/chat").withSockJS();
		resgistry.addEndpoint("/chat_forum").withSockJS();

		
	}

	
	
	
}
