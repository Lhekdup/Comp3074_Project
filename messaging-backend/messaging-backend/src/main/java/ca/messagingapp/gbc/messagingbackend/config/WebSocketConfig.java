package ca.messagingapp.gbc.messagingbackend.config;

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
        // Enable simple broker for topics, queues, and user destinations
        config.enableSimpleBroker("/topic", "/queue", "/user");
        // Set user destination prefix for user-specific messages
        config.setUserDestinationPrefix("/user");
        // Set application destination prefix for client sends
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register WebSocket endpoint with CORS enabled for all origins
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*");
    }
}
