package com.cibertec.service.implement;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.cibertec.dto.request.UsuarioActualizarCorreoDTO;
import com.cibertec.dto.request.UsuarioCreacionDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.UsuarioCreatedEvent;
import com.cibertec.dto.response.UsuarioDTO;
import com.cibertec.dto.response.PaisDTO;
import com.cibertec.dto.response.DepartamentoDTO;
import com.cibertec.dto.response.ProvinciaDTO;
import com.cibertec.dto.response.DistritoDTO;
import com.cibertec.entity.Departamento;
import com.cibertec.entity.Distrito;
import com.cibertec.entity.Pais;
import com.cibertec.entity.Provincia;
import com.cibertec.entity.Rol;
import com.cibertec.entity.Trabajador;
import com.cibertec.entity.Usuario;
import com.cibertec.mapper.UsuarioMapper;
import com.cibertec.client.UbicacionClient;
import com.cibertec.repository.IRolRepository;
import com.cibertec.repository.IUsuarioRepository;
import com.cibertec.service.UsuarioService;
import com.cibertec.util.ValidateText;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.persistence.NoResultException;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	private IRolRepository rolRepository;
	@Autowired
	private UbicacionClient ubicacionClient;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UsuarioMapper usuarioMapper;
	@Autowired
	private MessageProducerService messageProducerService;

	@Override
	public SuccessResponse<Page<UsuarioDTO>> listarUsuarios(Pageable pageable) {
		Page<UsuarioDTO> page = usuarioRepository.findAll(pageable)
				.map(usuarioMapper::toUsuarioDTO);

		if (page.isEmpty()) {
			throw new NoResultException("No se encontro ningun usuario");
		}

		return SuccessResponse.ok(page);
	}
	
	@Override
	public SuccessResponse<Page<UsuarioDTO>> listarUsuariosActivos(Pageable pageable) {
		Page<UsuarioDTO> page = usuarioRepository.findByIsDeleteFalseAndIsEnabledTrue(pageable)
				.map(usuarioMapper::toUsuarioDTO);

		if (page.isEmpty()) {
			throw new NoResultException("No se encontro ningun usuario");
		}

		return SuccessResponse.ok(page);
	}

	@Override
	public SuccessResponse<UsuarioDTO> crearUsuario(UsuarioCreacionDTO dto) {
		if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos: correo ya registrado");
		}

		Rol rol = rolRepository.findById(dto.getIdRol())
				.orElseThrow(() -> new NoResultException("No se encontro el rol con id: " + dto.getIdRol()));

		SuccessResponse<PaisDTO> paisResp = ubicacionClient.getPais(dto.getIdPais());
		PaisDTO paisDto = paisResp != null ? paisResp.getResponse() : null;
		if (paisDto == null) throw new NoResultException("No se encontro el pais con id: " + dto.getIdPais());
		SuccessResponse<DepartamentoDTO> depResp = ubicacionClient.getDepartamento(dto.getIdDepartamento());
		DepartamentoDTO depDto = depResp != null ? depResp.getResponse() : null;
		if (depDto == null) throw new NoResultException("No se encontro el departamento con id: " + dto.getIdDepartamento());
		SuccessResponse<ProvinciaDTO> provResp = ubicacionClient.getProvincia(dto.getIdProvincia());
		ProvinciaDTO provDto = provResp != null ? provResp.getResponse() : null;
		if (provDto == null) throw new NoResultException("No se encontro la provincia con id: " + dto.getIdProvincia());
		SuccessResponse<DistritoDTO> distResp = ubicacionClient.getDistrito(dto.getIdDistrito());
		DistritoDTO distDto = distResp != null ? distResp.getResponse() : null;
		if (distDto == null) throw new NoResultException("No se encontro el distrito con id: " + dto.getIdDistrito());

		Pais pais = new Pais(); 
		pais.setId(paisDto.getId()); 
		pais.setNombre(paisDto.getNombre());
		
		Departamento departamento = new Departamento(); 
		departamento.setId(depDto.getId()); 
		departamento.setNombre(depDto.getNombre());
		
		Provincia provincia = new Provincia(); 
		provincia.setId(provDto.getId()); 
		provincia.setNombre(provDto.getNombre());
		
		Distrito distrito = new Distrito(); 
		distrito.setId(distDto.getId()); 
		distrito.setNombre(distDto.getNombre());

		String nuevaClave = generarClaveRandom(10);
		dto.setClave(passwordEncoder.encode(nuevaClave));
		
		Usuario usuario = usuarioMapper.toUsuario(null, dto, rol, pais, departamento, provincia, distrito);
		
		UsuarioDTO usuarioDTO = usuarioMapper.toUsuarioDTO(usuarioRepository.save(usuario));
		
		UsuarioCreatedEvent usuarioEvent = UsuarioCreatedEvent.builder()
				.nombre(usuarioDTO.getNombre())
				.apellido(usuarioDTO.getApellido())
				.tipoDocumento(usuarioDTO.getTipoDocumento())
				.nmrDocumento(usuarioDTO.getNmrDocumento())
				.email(usuarioDTO.getEmail())
				.telefono(usuarioDTO.getTelefono())
				.rol(usuarioDTO.getRol())
				.clave(nuevaClave)
				.build();
		
		messageProducerService.sendUsuarioCreado(usuarioEvent);

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
		usuario.setDelete(true);
		usuarioRepository.save(usuario);
		return SuccessResponse.ok("Usuario eliminado");
	}

	@Override
	public SuccessResponse<UsuarioDTO> actualizarCorreo(Integer id, UsuarioActualizarCorreoDTO dto) {
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
		Usuario usuario = usuarioRepository.findById(id)
			.orElseThrow(() -> new NoResultException("No se encontro el usuario con id: " + id));

		String nuevaClave = generarClaveRandom(10);

		usuario.setClave(passwordEncoder.encode(nuevaClave));
		UsuarioDTO usuarioDTO = usuarioMapper.toUsuarioDTO(usuarioRepository.save(usuario));
		
		UsuarioCreatedEvent usuarioEvent = UsuarioCreatedEvent.builder()
				.nombre(usuarioDTO.getNombre())
				.apellido(usuarioDTO.getApellido())
				.tipoDocumento(usuarioDTO.getTipoDocumento())
				.nmrDocumento(usuarioDTO.getNmrDocumento())
				.email(usuarioDTO.getEmail())
				.telefono(usuarioDTO.getTelefono())
				.rol(usuarioDTO.getRol())
				.clave(nuevaClave)
				.build();

		messageProducerService.sendPasswordReset(usuarioEvent);

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

	@Override
	public SuccessResponse<String> cambiarEstado(Integer id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro el usuario con id: " + id));
		usuario.setEnabled(!usuario.isEnabled());
		usuarioRepository.save(usuario);
		return SuccessResponse.ok("Estado del usuario actualizado exitosamente");
	}

	@Override
	public SuccessResponse<UsuarioDTO> obtenerUsuarioPorCorreo(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new NoResultException("No se encontro el usuario con email: " + email));

		return SuccessResponse.ok(usuarioMapper.toUsuarioDTO(usuario));
	}

}