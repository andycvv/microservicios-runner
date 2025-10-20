package com.cibertec.user.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.user.dto.response.SuccessResponse;
import com.cibertec.user.model.Distrito;

@Service
public interface DistritoService {

	public ResponseEntity<SuccessResponse<Distrito>> findByIdDistrito(Integer id);
	
	public ResponseEntity<SuccessResponse<List<Distrito>>> findAllDistrito();
	
	public ResponseEntity<SuccessResponse<Distrito>> saveDistrito(Distrito distrito);
	
	public ResponseEntity<SuccessResponse<Distrito>> updateDistrito(Distrito distrito, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteDistrito(Integer i);
}
