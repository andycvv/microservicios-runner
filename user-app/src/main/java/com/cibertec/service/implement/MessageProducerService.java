package com.cibertec.service.implement;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cibertec.dto.response.UsuarioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class MessageProducerService {
    @Value("${app.queue.usuario-creado.name}")
    private String usuarioCreadoQueue;

    @Value("${app.queue.password-reset.name}")
    private String passwordResetQueue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public void sendUsuarioCreado(Object event) {
        try {
            String msg = mapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend("", usuarioCreadoQueue, msg);
        } catch (Exception e) {
            throw new RuntimeException("Error serializando evento UsuarioCreado", e);
        }
    }

    public void sendPasswordReset(Object event) {
        try {
            String msg = mapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend("", passwordResetQueue, msg);
        } catch (Exception e) {
            throw new RuntimeException("Error serializando evento PasswordReset", e);
        }
    }
}
