package com.cibertec.service.implement;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.cibertec.dto.request.UsuarioActualizarDTO;
import com.cibertec.dto.request.UsuarioCreacionDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.UsuarioDTO;
import com.cibertec.entity.Departamento;
import com.cibertec.entity.Distrito;
import com.cibertec.entity.Pais;
import com.cibertec.entity.Provincia;
import com.cibertec.entity.Rol;
import com.cibertec.entity.Usuario;
import com.cibertec.mapper.UsuarioMapper;
import com.cibertec.repository.IDepartamentoRepository;
import com.cibertec.repository.IDistritoRepository;
import com.cibertec.repository.IPaisRepository;
import com.cibertec.repository.IProvinciaRepository;
import com.cibertec.repository.IRolRepository;
import com.cibertec.repository.IUsuarioRepository;
import com.cibertec.service.UsuarioService;
import com.cibertec.util.ValidateText;

import jakarta.persistence.NoResultException;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	private IRolRepository rolRepository;
	@Autowired
	private IPaisRepository paisRepository;
	@Autowired
	private IDepartamentoRepository departamentoRepository;
	@Autowired
	private IProvinciaRepository provinciaRepository;
	@Autowired
	private IDistritoRepository distritoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UsuarioMapper usuarioMapper;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public SuccessResponse<List<UsuarioDTO>> listarUsuarios() {
		List<UsuarioDTO> list = usuarioRepository.findAll().stream()
				.map(usuarioMapper::toUsuarioDTO)
				.toList();

		if (list.isEmpty()) {
			throw new NoResultException("No se encontro ningun usuario");
		}

		return SuccessResponse.ok(list);
	}
	
	@Override
	public SuccessResponse<List<UsuarioDTO>> listarUsuariosActivos() {
		List<UsuarioDTO> list = usuarioRepository.findAll().stream()
				.filter(u -> !u.isDelete() && u.isEnabled())
				.map(usuarioMapper::toUsuarioDTO)
				.toList();

		if (list.isEmpty()) {
			throw new NoResultException("No se encontro ningun usuario");
		}

		return SuccessResponse.ok(list);
	}



	@Override
	public SuccessResponse<UsuarioDTO> crearUsuario(UsuarioCreacionDTO dto) {
		if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos: correo ya registrado");
		}

		Rol rol = rolRepository.findById(dto.getIdRol())
				.orElseThrow(() -> new NoResultException("No se encontro el rol con id: " + dto.getIdRol()));

		Pais pais = paisRepository.findById(dto.getIdPais()).orElseThrow(
				() -> new NoResultException("No se encontro el pais con id: " + dto.getIdPais()));
		
		Departamento departamento = departamentoRepository.findById(dto.getIdDepartamento()).orElseThrow(
				() -> new NoResultException("No se encontro el departamento con id: " + dto.getIdDepartamento()));

		Provincia provincia = provinciaRepository.findById(dto.getIdProvincia()).orElseThrow(
				() -> new NoResultException("No se encontro la provincia con id: " + dto.getIdProvincia()));
		
		Distrito distrito = distritoRepository.findById(dto.getIdDistrito()).orElseThrow(
				() -> new NoResultException("No se encontro el distrito con id: " + dto.getIdDistrito()));

		String nuevaClave = generarClaveRandom(10);
		dto.setClave(passwordEncoder.encode(nuevaClave));
		
		Usuario usuario = usuarioMapper.toUsuario(null, dto, rol, pais, departamento, provincia, distrito);
		
		UsuarioDTO usuarioDTO = usuarioMapper.toUsuarioDTO(usuarioRepository.save(usuario));
		
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(usuarioDTO.getEmail());
			msg.setSubject("Nueva contraseña");
			msg.setText("Su nueva contraseña es: " + nuevaClave + "\nPor favor cámbiela al iniciar sesión.");
			msg.setFrom("pacmanbbva@gmail.com");
			mailSender.send(msg);
		} catch (Exception ex) {
			System.err.println("Error enviando email: " + ex.getMessage());
		}

		return SuccessResponse.created(usuarioDTO);
	}

	@Override
	public SuccessResponse<UsuarioDTO> obtenerUsuarioPorId(Integer id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro el usuario con id: " + id));

		return SuccessResponse.ok(usuarioMapper.toUsuarioDTO(usuario));
	}

	@Override
	public SuccessResponse<String> eliminarUsuario(Integer id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro el usuario con id: " + id));
		usuario.setEnabled(false);
		usuario.setDelete(true);
		usuarioRepository.save(usuario);
		return SuccessResponse.ok("Usuario eliminado");
	}

	@Override
	public SuccessResponse<UsuarioDTO> actualizarCorreo(Integer id, UsuarioActualizarDTO dto) {
		usuarioRepository.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro el usuario con id: " + id));

		Optional<Usuario> usuario = usuarioRepository.findByEmail(dto.getEmail());
		if (usuario.isPresent() && !usuario.get().getId().equals(id)) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos: correo ya registrado");
		}

		usuario.get().setEmail(dto.getEmail());
		Usuario actualizado = usuarioRepository.save(usuario.get());
		return SuccessResponse.ok(usuarioMapper.toUsuarioDTO(actualizado));
	}

	@Override
	public SuccessResponse<String> actualizarClave(Integer id) {
		Usuario user = usuarioRepository.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro el usuario con id: " + id));

		String nuevaClave = generarClaveRandom(10);

		user.setClave(passwordEncoder.encode(nuevaClave));
		usuarioRepository.save(user);

		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(user.getEmail());
			msg.setSubject("Nueva contraseña");
			msg.setText("Su nueva contraseña es: " + nuevaClave + "\nPor favor cámbiela al iniciar sesión.");
			msg.setFrom("pacmanbbva@gmail.com");
			mailSender.send(msg);
		} catch (Exception ex) {
			System.err.println("Error enviando email: " + ex.getMessage());
		}

		return SuccessResponse.ok("Se actualizó la contraseña y se envió al correo del usuario.");
	}

	private String generarClaveRandom(int length) {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "@#$%&*-_";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int idx = random.nextInt(chars.length());
			sb.append(chars.charAt(idx));
		}
		return sb.toString();
	}

}