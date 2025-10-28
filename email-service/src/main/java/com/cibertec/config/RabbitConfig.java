package com.cibertec.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${app.queue.usuario-creado.name}")
    private String usuarioCreadoQueueName;

    @Value("${app.queue.password-reset.name}")
    private String passwordResetQueueName;

    @Value("${app.queue.boleta-creada.namee}")
    private String boletaCreadaQueueName;
    
    @Bean
    Queue usuarioCreadoQueue() {
        return new Queue(usuarioCreadoQueueName, true);
    }

    @Bean
    Queue passwordResetQueue() {
        return new Queue(passwordResetQueueName, true);
    }
    
    @Bean
    Queue boletaCreadaQueue() {
		return new Queue(boletaCreadaQueueName, true);
	}
}
