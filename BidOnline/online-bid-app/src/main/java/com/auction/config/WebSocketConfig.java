package com.auction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 1. Outbound channels: Server broadcasts updates here
        config.enableSimpleBroker("/topic");
        
        // 2. Inbound channels: Participants target their bids here
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 3. The connection URL door that participants open
        registry.addEndpoint("/ws-auction")
                .setAllowedOriginPatterns("*")
                .withSockJS(); // Fallback for older browsers
    }
}
