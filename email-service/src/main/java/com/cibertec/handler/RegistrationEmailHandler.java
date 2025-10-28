package com.cibertec.handler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.dto.UsuarioCreatedEvent;
import com.cibertec.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class RegistrationEmailHandler {
    @Autowired
    private EmailService emailService;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @RabbitListener(queues = "${app.queue.usuario-creado.name}")
    public void handleUsuarioCreado(String message) {
        try {
            UsuarioCreatedEvent event = objectMapper.readValue(message, UsuarioCreatedEvent.class);
            emailService.enviarCorreoUsuarioRegistrado(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "${app.queue.password-reset.name}")
    public void handlePasswordReset(String message) {
        try {
        	UsuarioCreatedEvent event = objectMapper.readValue(message, UsuarioCreatedEvent.class);
            emailService.enviarCorreoPasswordReset(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
