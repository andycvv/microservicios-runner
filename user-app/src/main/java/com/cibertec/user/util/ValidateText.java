package com.cibertec.user.util;

import org.springframework.stereotype.Component;

@Component
public class ValidateText {

	public void isRequired(String texto, String fieldName) {
		if (texto == null || texto.trim().isEmpty()) {
			throw new IllegalArgumentException(fieldName + " es obligatorio.");
		}
	}

	public void hasValidLength(String texto, int min, int max, String fieldName) {
		int longitud = texto.trim().length();
		if (longitud < min || longitud > max) {
			throw new IllegalArgumentException(fieldName + " debe tener entre " + min + " y " + max + " caracteres.");
		}
	}

	public void hasOnlyLettersAndSpaces(String texto, String fieldName) {
		if (!texto.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$") || texto == null) {
			throw new IllegalArgumentException(fieldName + " solo puede contener letras y espacios.");
		}
	}

	public void hasOnlyNumbers(String texto, String fieldName) {
		if (!texto.matches("^\\d+$")) {
			throw new IllegalArgumentException(fieldName + " solo puede contener números.");
		}
	}

	public void hasNoneCharacterDanger(String texto, String fieldName) {
		if (texto.matches(".*[<>\"'%;].*")) {
			throw new IllegalArgumentException(fieldName + " contiene caracteres no permitidos.");
		}
	}

	public void isValidGmail(String email) {
		if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
			throw new IllegalArgumentException("El correo no tiene un formato válido de Gmail.");
		}
	}
}
