package com.cibertec.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Distrito;
import com.cibertec.repository.IDistritoRepository;
import com.cibertec.service.DistritoService;

import jakarta.persistence.NoResultException;

@Service
public class DistritoServiceImp implements DistritoService {
    @Autowired
    private IDistritoRepository repository;
	
    @Override
    public ResponseEntity<SuccessResponse<Distrito>> findByIdDistrito(Integer id) {
        Distrito distrito = repository.findById(id).orElse(null);

        if (distrito == null) {
			throw new NoResultException("No se encontro el codigo de la distrito");
        }
            SuccessResponse<Distrito> success = SuccessResponse.<Distrito>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(distrito)
                .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<List<Distrito>>> findAllDistrito() {
        List<Distrito> distritos = repository.findAll();

        if (distritos.isEmpty()) {
			throw new NoResultException("No se encontro ningun distrito");
        }
            SuccessResponse<List<Distrito>> success = SuccessResponse.<List<Distrito>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(distritos)
                .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Distrito>> saveDistrito(Distrito distrito) {
    	
		if(repository.existsByNombre(distrito.getNombre())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}

        Distrito dis = new Distrito();
		dis.setNombre(distrito.getNombre());
		
        Distrito savedDistrito = repository.save(dis);

        SuccessResponse<Distrito> success = SuccessResponse.<Distrito>builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.CREATED.value())
            .success(HttpStatus.CREATED.getReasonPhrase())
            .response(savedDistrito)
            .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

	@Override
	public ResponseEntity<SuccessResponse<Distrito>> updateDistrito(Distrito distrito, Integer id) {

		if(repository.existsByNombre(distrito.getNombre())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Distrito existente = repository.findById(id).orElse(null);

        if (existente == null) {
        	throw new NoResultException("No se encontro el codigo de material");
        }

        existente.setNombre(distrito.getNombre());
        
        Distrito actualizado = repository.save(existente);

        SuccessResponse<Distrito> success = SuccessResponse.<Distrito>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(actualizado)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<String>> deleteDistrito(Integer id) {
		Distrito distrito= repository.findById(id).orElse(null);

        if (distrito == null) {
        	throw new NoResultException("No se encontro el codigo de el distrito");
        }
        
        repository.delete(distrito);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Distrito eliminado correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}
}
