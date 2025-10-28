package com.cibertec.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cibertec.dto.UsuarioCreatedEvent;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String from;

	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	public void enviarCorreoUsuarioRegistrado(UsuarioCreatedEvent usuarioDTO) {
		if (usuarioDTO == null || usuarioDTO.getEmail() == null) {
			logger.warn("usuarioDTO o email nulo, no se enviará correo");
			return;
		}

		String email = usuarioDTO.getEmail();
		String nombre = usuarioDTO.getNombre() != null ? usuarioDTO.getNombre() : "";
		String apellido = usuarioDTO.getApellido() != null ? usuarioDTO.getApellido() : "";
		String tipoDocumento = usuarioDTO.getTipoDocumento() != null ? usuarioDTO.getTipoDocumento() : "";
		String nmrDocumento = usuarioDTO.getNmrDocumento() != null ? usuarioDTO.getNmrDocumento() : "";
		String rol = usuarioDTO.getRol() != null ? usuarioDTO.getRol() : "";
		String telefono = usuarioDTO.getTelefono() != null ? usuarioDTO.getTelefono() : "";
		String tempPwd = usuarioDTO.getClave() != null ? usuarioDTO.getClave() : "";

		String subject = "Bienvenido a Runner Store - Tu contraseña temporal";

		String html = "<!doctype html>"
			+ "<html><body style=\"font-family:Arial,Helvetica,sans-serif;background:#f4f6f8;margin:0;padding:0;\">"
			+ "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tr><td align=\"center\" style=\"padding:20px 10px;\">"
			+ "<table width=\"600\" style=\"background:#ffffff;border-radius:8px;overflow:hidden;\">"
			+ "<tr><td style=\"background:#1f6feb;padding:24px;text-align:center;color:#ffffff;\"><h1 style=\"margin:0;font-size:20px;\">Runner Store</h1></td></tr>"
			+ "<tr><td style=\"padding:24px;color:#1f2937;\">"
			+ "<p style=\"margin:0 0 8px 0;\">Hola <strong>" + escapeHtml(nombre + (apellido.isEmpty() ? "" : " " + apellido)) + "</strong>,</p>"
			+ "<p style=\"margin:0 0 16px 0;\">Tu cuenta en <strong>Runner Store</strong> ha sido creada correctamente. Debajo encontrarás tu contraseña temporal y los datos de la cuenta.</p>"
			+ "<div style=\"background:#f1f5f9;padding:16px;border-radius:6px;margin:16px 0;text-align:center;\">"
			+ "<p style=\"margin:0 0 8px 0;font-size:14px;color:#374151;\">Contraseña temporal</p>"
			+ "<p style=\"margin:0;font-size:18px;font-weight:700;color:#0f172a;\">" + escapeHtml(tempPwd) + "</p>"
			+ "<p style=\"margin:8px 0 0 0;font-size:13px;color:#6b7280;\">Por seguridad cámbiala al iniciar sesión.</p>"
			+ "</div>"
			+ "<hr style=\"border:none;border-top:1px solid #e6e9ee;margin:20px 0;\">"
			+ "<p style=\"margin:0 0 8px 0;\">Datos de la cuenta:</p>"
			+ "<ul style=\"margin:0 0 16px 18px;color:#374151;\">"
			+ "<li>Nombre: <strong>" + escapeHtml(nombre + (apellido.isEmpty() ? "" : " " + apellido)) + "</strong></li>"
			+ "<li>Documento: <strong>" + escapeHtml(tipoDocumento + " - " + nmrDocumento) + "</strong></li>"
			+ "<li>Rol: <strong>" + escapeHtml(rol) + "</strong></li>"
			+ "<li>Teléfono: <strong>" + escapeHtml(telefono) + "</strong></li>"
			+ "<li>Email: <strong>" + escapeHtml(email) + "</strong></li>"
			+ "</ul>"
			+ "<p style=\"margin:0;color:#6b7280;font-size:13px;\">Si no solicitaste esta cuenta, ignora este correo o contacta a soporte en <a href=\"mailto:support@runnerstore.com\" style=\"color:#1f6feb;\">support@runnerstore.com</a>.</p>"
			+ "</td></tr>"
			+ "<tr><td style=\"background:#f9fafb;padding:16px;text-align:center;color:#9aa4b2;font-size:12px;\">© " + java.time.Year.now().getValue() + " Runner Store. Todos los derechos reservados.</td></tr>"
			+ "</table></td></tr></table></body></html>";

		String plain = "Hola " + (apellido.isEmpty() ? nombre : nombre + " " + apellido) + ",\n\n"
			+ "Tu cuenta en Runner Store ha sido creada.\n"
			+ "Contraseña temporal: " + tempPwd + "\nPor favor cámbiala al iniciar sesión.\n\n"
			+ "Datos de la cuenta:\n"
			+ "- Nombre: " + (apellido.isEmpty() ? nombre : nombre + " " + apellido) + "\n"
			+ "- Documento: " + tipoDocumento + " - " + nmrDocumento + "\n"
			+ "- Rol: " + rol + "\n"
			+ "- Teléfono: " + telefono + "\n"
			+ "- Email: " + email + "\n\n"
			+ "Si no solicitaste esta cuenta, ignora este mensaje o contacta a support@runnerstore.com\n\n- El equipo de Runner Store";

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom(from);
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(plain, html);
			mailSender.send(message);
			logger.info("Correo de bienvenida enviado a: {}", email);
		} catch (MessagingException ex) {
			logger.error("Error al enviar correo a {}: {}", email, ex.getMessage());
		}
	}

	public void enviarCorreoPasswordReset(UsuarioCreatedEvent event) {
		if (event == null || event.getEmail() == null) {
			logger.warn("evento nulo o email nulo, no se enviará correo de reset");
			return;
		}

		String email = event.getEmail();
		String nombre = event.getNombre() != null ? event.getNombre() : "";
		String apellido = event.getApellido() != null ? event.getApellido() : "";
		String tempPwd = event.getClave() != null ? event.getClave() : ""; // contraseña temporal establecida por el admin

		String subject = "Tu contraseña ha sido restablecida - Runner Store";

		String loginLink = "http://localhost:4200";

		String html = "<!doctype html>"
			+ "<html><body style=\"font-family:Arial,Helvetica,sans-serif;background:#f4f6f8;margin:0;padding:0;\">"
			+ "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tr><td align=\"center\" style=\"padding:20px 10px;\">"
			+ "<table width=\"600\" style=\"background:#ffffff;border-radius:8px;overflow:hidden;\">"
			+ "<tr><td style=\"background:#1f6feb;padding:24px;text-align:center;color:#ffffff;\"><h1 style=\"margin:0;font-size:20px;\">Runner Store</h1></td></tr>"
			+ "<tr><td style=\"padding:24px;color:#1f2937;\">"
			+ "<p style=\"margin:0 0 8px 0;\">Hola <strong>" + escapeHtml(nombre + (apellido.isEmpty() ? "" : " " + apellido)) + "</strong>,</p>"
			+ "<p style=\"margin:0 0 16px 0;\">Un administrador ha restablecido la contraseña de tu cuenta. A continuación tienes la contraseña temporal que debes usar para iniciar sesión y cambiarla inmediatamente.</p>"
			+ "<div style=\"background:#fff4f4;padding:16px;border-radius:6px;margin:16px 0;text-align:center;border:1px solid #fde2e2;\">"
			+ "<p style=\"margin:0 0 8px 0;font-size:14px;color:#374151;\">Contraseña temporal</p>"
			+ "<p style=\"margin:0;font-size:18px;font-weight:700;color:#b91c1c;\">" + escapeHtml(tempPwd) + "</p>"
			+ "<p style=\"margin:8px 0 0 0;font-size:13px;color:#6b7280;\">Por seguridad, cámbiala en tu perfil tras iniciar sesión.</p>"
			+ "</div>"
			+ "<p style=\"margin:0 0 16px 0;\">Puedes iniciar sesión usando el siguiente enlace:</p>"
			+ "<p style=\"text-align:center;margin:0 0 24px 0;\">"
			+ "<a href=\"" + loginLink + "\" style=\"display:inline-block;padding:12px 20px;background:#1f6feb;color:#fff;text-decoration:none;border-radius:6px;font-weight:600;\">Ir a Runner Store</a>"
			+ "</p>"
			+ "<p style=\"margin:0 0 8px 0;color:#6b7280;\">Si tienes problemas para iniciar sesión, contacta a soporte en <a href=\"mailto:support@runnerstore.com\" style=\"color:#1f6feb;\">support@runnerstore.com</a>.</p>"
			+ "</td></tr>"
			+ "<tr><td style=\"background:#f9fafb;padding:16px;text-align:center;color:#9aa4b2;font-size:12px;\">© " + java.time.Year.now().getValue() + " Runner Store. Todos los derechos reservados.</td></tr>"
			+ "</table></td></tr></table></body></html>";

		String plain = "Hola " + (apellido.isEmpty() ? nombre : nombre + " " + apellido) + ",\n\n"
			+ "Un administrador ha restablecido la contraseña de tu cuenta.\n"
			+ "Contraseña temporal: " + tempPwd + "\n"
			+ "Por favor, inicia sesión y cambia tu contraseña inmediatamente: " + loginLink + "\n\n"
			+ "Si no solicitaste este cambio, contacta a support@runnerstore.com inmediatamente.\n\n- El equipo de Runner Store";

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom(from);
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(plain, html);
			mailSender.send(message);
			logger.info("Correo de password reset (admin) enviado a: {}", email);
		} catch (MessagingException ex) {
			logger.error("Error al enviar correo de password reset a {}: {}", email, ex.getMessage());
		}
	}

	private String escapeHtml(String input) {
		if (input == null) return "";
		return input.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
	}
}