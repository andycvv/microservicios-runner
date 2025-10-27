package com.cibertec.user.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Departamento;
import com.cibertec.entity.Distrito;
import com.cibertec.entity.Provincia;
import com.cibertec.entity.Rol;
import com.cibertec.entity.Usuario;
import com.cibertec.user.dto.request.UpdatePasswordDTO;
import com.cibertec.user.dto.request.UpdateUserDTO;
import com.cibertec.user.dto.request.UsuarioCreacionDTO;
import com.cibertec.user.dto.response.UserResponse;
import com.cibertec.user.mapper.UsuarioMapper;
import com.cibertec.user.repository.IDepartamentoRepository;
import com.cibertec.user.repository.IDistritoRepository;
import com.cibertec.user.repository.IProvinciaRepository;
import com.cibertec.user.repository.IRolRepository;
import com.cibertec.user.repository.IUsuarioRepository;
import com.cibertec.user.service.AccountService;
import com.cibertec.user.util.ValidateText;

import jakarta.persistence.NoResultException;

@Service
public class AccountServiceImp implements AccountService {

	@Autowired
	private ValidateText vt;
	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	private IRolRepository rolRepository;
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
	
	@Override
	public Usuario findByEmailInternal(String email) {
		return usuarioRepository.findByEmail(email).orElse(null);
	}

	@Override
	public void registerUser(UsuarioCreacionDTO dto) {

		if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new IllegalArgumentException("El correo ya está registrado");
		}
		
		Rol rol = rolRepository.findByNombre("USER")
				.orElseThrow(() -> new NoResultException("No se encontro el rol con nombre: USER"));

		Departamento departamento = departamentoRepository.findById(dto.getIdDepartamento()).orElseThrow(
				() -> new NoResultException("No se encontro el departamento con id: " + dto.getIdDepartamento()));

		Provincia provincia = provinciaRepository.findById(dto.getIdProvincia()).orElseThrow(
				() -> new NoResultException("No se encontro la provincia con id: " + dto.getIdProvincia()));
		
		Distrito distrito = distritoRepository.findById(dto.getIdDistrito()).orElseThrow(
				() -> new NoResultException("No se encontro el distrito con id: " + dto.getIdDistrito()));

		dto.setClave(passwordEncoder.encode(dto.getClave()));
		
		Usuario usuario = usuarioMapper.toUsuario(null, dto, rol, departamento, provincia, distrito);
		
		usuarioMapper.toUsuarioDTO(usuarioRepository.save(usuario));
	}

	@Override
	public void updateUser(UpdateUserDTO request) {
		String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

		Usuario user = usuarioRepository.findByEmail(emailAutenticado)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		vt.isRequired(request.getNombre(), "Nombre");
		vt.hasOnlyLettersAndSpaces(request.getNombre(), "Nombre");
		vt.hasValidLength(request.getNombre(), 2, 30, "Nombre");

		vt.isRequired(request.getApellido(), "Apellido");
		vt.hasValidLength(request.getApellido(), 2, 30, "Apellido");
		vt.hasOnlyLettersAndSpaces(request.getApellido(), "Apellido");

		vt.isRequired(request.getDni(), "Nmr de documento");
		vt.hasOnlyNumbers(request.getDni(), "Nmr de documento");
		vt.hasValidLength(request.getDni(), 8, 12, "Nmr de documento");

		vt.isRequired(request.getTelefono(), "Telefono");
		vt.hasOnlyNumbers(request.getTelefono(), "Telefono");
		vt.hasValidLength(request.getTelefono(), 9, 12, "Telefono");

		user.setNombre(request.getNombre());
		user.setApellido(request.getApellido());
		user.setNmrDocumento(request.getDni());
		user.setTelefono(request.getTelefono());

		usuarioRepository.save(user);

	}

	@Override
	public void updatePassword(UpdatePasswordDTO request) {
		String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

		vt.isRequired(request.getClaveActual(), "La contraseña actual");
//		vt.hasNoneCharacterDanger(request.getClaveActual(), "La contraseña actual");

		vt.isRequired(request.getNuevaClave(), "La nueva contraseña");

		Usuario user = usuarioRepository.findByEmail(emailAutenticado)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		if (!passwordEncoder.matches(request.getClaveActual(), user.getClave())) {
			throw new BadCredentialsException("La contraseña actual es incorrecta");
		}

		vt.hasValidLength(request.getNuevaClave(), 5, 60, "Contraseña");
		vt.hasNoneCharacterDanger(request.getNuevaClave(), "Contraseña");

		String newPasswordEncrypted = passwordEncoder.encode(request.getNuevaClave());
		user.setClave(newPasswordEncrypted);
		usuarioRepository.save(user);
	}

	@Override
	public UserResponse getUsuarioLogueado() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			Usuario user = usuarioRepository.findByEmail(authentication.getName())
					.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
			
			UserResponse response = new UserResponse();

			response.setNombre(user.getNombre());
			response.setApellido(user.getApellido());
			response.setNmrDocumento(user.getNmrDocumento());
			response.setTelefono(user.getTelefono());
			response.setCorreo(user.getEmail());
			response.setDistrito(user.getDistrito());

			return response;

		}

		throw new UsernameNotFoundException("Usuario no autentificado");
	}

}
