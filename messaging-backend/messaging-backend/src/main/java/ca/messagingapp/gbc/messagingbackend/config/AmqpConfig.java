package ca.messagingapp.gbc.messagingbackend.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    public FanoutExchange groupFanout() {
        return new FanoutExchange("grp.broadcast");
    }

    @Bean
    public Queue serverQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding(FanoutExchange ex, Queue q) {
        return BindingBuilder.bind(q).to(ex);
    }
}
