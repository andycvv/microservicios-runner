package com.cibertec.user.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.user.dto.request.LoginDTO;
import com.cibertec.user.dto.request.RegisterUserDTO;
import com.cibertec.user.dto.request.UpdatePasswordDTO;
import com.cibertec.user.dto.request.UpdateUserDTO;
import com.cibertec.user.dto.response.UserResponse;
import com.cibertec.user.model.Distrito;
import com.cibertec.user.model.Usuario;
import com.cibertec.user.repository.IDistritoRepository;
import com.cibertec.user.repository.IUsuarioRepository;
import com.cibertec.user.service.AccountService;
import com.cibertec.user.util.ValidateText;


@Service
public class AccountServiceImp implements AccountService {

	@Autowired
	private ValidateText vt;
	@Autowired
	private IUsuarioRepository userRepository;
	@Autowired
	private IDistritoRepository distritoRep;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Usuario findByEmailInternal(String email) {
		return userRepository.findByCorreo(email).orElse(null);
	}

	@Override
	public void registerUser(RegisterUserDTO request) {

		if (userRepository.findByCorreo(request.getCorreo()).isPresent()) {
			throw new IllegalArgumentException("El correo ya está registrado");
		}

		vt.isRequired(request.getNombre(), "Nombre");
		vt.hasOnlyLettersAndSpaces(request.getNombre(), "Nombre");
		vt.hasValidLength(request.getNombre(), 2, 30, "Nombre");

		vt.hasValidLength(request.getApellido(), 2, 30, "Apellido");
		vt.hasOnlyLettersAndSpaces(request.getApellido(), "Apellido");

		vt.hasOnlyNumbers(request.getNmrDocumento(), "Número de documento");
		vt.hasValidLength(request.getNmrDocumento(), 8, 12, "Número de documento");

		vt.hasOnlyNumbers(request.getTelefono(), "Teléfono");
		vt.hasValidLength(request.getTelefono(), 9, 12, "Teléfono");

		vt.isValidGmail(request.getCorreo());

		vt.hasValidLength(request.getContrasenia(), 5, 60, "Contraseña");
		vt.hasNoneCharacterDanger(request.getContrasenia(), "Contraseña");

		Usuario user = new Usuario();
		user.setNombre(request.getNombre());
		user.setApellido(request.getApellido());
		user.setNmrDocumento(request.getNmrDocumento());
		user.setTelefono(request.getTelefono());
		user.setCorreo(request.getCorreo());
		user.setEstado(true);
		user.setContrasenia(passwordEncoder.encode(request.getContrasenia()));
		user.setRol("USER");

		Distrito d = distritoRep.findById(request.getIdDto()).orElse(null);
		if (d != null) {
			user.setIdDto(request.getIdDto());
		} else {
			throw new RuntimeException("No se encontro un distrito");
		}
		userRepository.save(user);
	}

	@Override
	public void updateUser(UpdateUserDTO request) {
		String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

		Usuario user = userRepository.findByCorreo(emailAutenticado)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		vt.isRequired(request.getNombre(), "Nombre");
		vt.hasOnlyLettersAndSpaces(request.getNombre(), "Nombre");
		vt.hasValidLength(request.getNombre(), 2, 30, "Nombre");

		vt.isRequired(request.getApellido(), "Apellido");
		vt.hasValidLength(request.getApellido(), 2, 30, "Apellido");
		vt.hasOnlyLettersAndSpaces(request.getApellido(), "Apellido");

		vt.isRequired(request.getNmrDocumento(), "Nmr de documento");
		vt.hasOnlyNumbers(request.getNmrDocumento(), "Nmr de documento");
		vt.hasValidLength(request.getNmrDocumento(), 8, 12, "Nmr de documento");

		vt.isRequired(request.getTelefono(), "Telefono");
		vt.hasOnlyNumbers(request.getTelefono(), "Telefono");
		vt.hasValidLength(request.getTelefono(), 9, 12, "Telefono");

		user.setNombre(request.getNombre());
		user.setApellido(request.getApellido());
		user.setNmrDocumento(request.getNmrDocumento());
		user.setTelefono(request.getTelefono());

		userRepository.save(user);

	}
	
	
	
	@Override
	public String signin(LoginDTO request) {
//		vt.hasNoneCharacterDanger(request.getCorreo(), "Correo");
//		vt.hasNoneCharacterDanger(request.getContrasenia(), "Contraseña");
//		Usuario usuario = userRepository.findByCorreo(request.getCorreo()).orElse(null);
//
//		if (usuario != null && passwordEncoder.matches(request.getContrasenia(), usuario.getContrasenia())) {
//			return jwtService.generateToken(usuario.getCorreo(), usuario.getRol(), usuario.getNombre());
//		} else {
//			throw new BadCredentialsException("Usuario y/o contraseña incorrecta");
//		}
		return null;
	}

	@Override
	public void updatePassword(UpdatePasswordDTO request) {
		String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

		vt.isRequired(request.getContraseniaActual(), "La contraseña actual");
		vt.hasNoneCharacterDanger(request.getContraseniaActual(), "La contraseña actual");

		vt.isRequired(request.getNuevaContrasenia(), "La nueva contraseña");

		Usuario user = userRepository.findByCorreo(emailAutenticado)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		if (!passwordEncoder.matches(request.getContraseniaActual(), user.getContrasenia())) {
			throw new BadCredentialsException("La contraseña actual es incorrecta");
		}

		vt.hasValidLength(request.getNuevaContrasenia(), 5, 60, "Contraseña");
		vt.hasNoneCharacterDanger(request.getNuevaContrasenia(), "Contraseña");

		String newPasswordEncrypted = passwordEncoder.encode(request.getNuevaContrasenia());
		user.setContrasenia(newPasswordEncrypted);
		userRepository.save(user);
	}

	@Override
	public UserResponse getUsuarioLogueado() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			Usuario user = userRepository.findByCorreo(authentication.getName())
					.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
			
			UserResponse response = new UserResponse();

			response.setNombre(user.getNombre());
			response.setApellido(user.getApellido());
			response.setNmrDocumento(user.getNmrDocumento());
			response.setTelefono(user.getTelefono());
			response.setCorreo(user.getCorreo());
			response.setDistrito(user.getDistrito());

			return response;

		}

		throw new UsernameNotFoundException("Usuario no autentificado");
	}

}
