package com.cibertec.service.implement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.client.TiendaClient;
import com.cibertec.dto.request.TrabajadorCreacionDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.TiendaDTO;
import com.cibertec.dto.response.TrabajadorDTO;
import com.cibertec.dto.response.UsuarioDTO;
import com.cibertec.entity.Trabajador;
import com.cibertec.mapper.TrabajadorMapper;
import com.cibertec.repository.ITrabajadorRepository;
import com.cibertec.service.TrabajadorService;
import com.cibertec.service.UsuarioService;

@Service
public class TrabajadorServiceImp implements TrabajadorService {
    @Autowired
    private ITrabajadorRepository trabajadorRepo;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TiendaClient tiendaClient;
    @Autowired
    private TrabajadorMapper trabajadorMapper;

    @Override
    @Transactional
    public Map<String, Object> registrar(TrabajadorCreacionDTO trabajador) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            SuccessResponse<TiendaDTO> tiendaRes = tiendaClient.obtenerTiendaPorId(trabajador.getIdTienda());

            SuccessResponse<UsuarioDTO> res = usuarioService.crearUsuario(trabajador.getUsuario());
            Trabajador t = new Trabajador();
            Integer usuarioId = res.getResponse().getId();
            Integer tiendaId = tiendaRes.getResponse().getId();
            t.setIdUsuario(usuarioId);
            t.setIdTienda(tiendaId);
            t.setEnabled(true);
            t.setDelete(false);
            t.setHorasLaborales(trabajador.getHorasLaborales());
            t.setSalario(trabajador.getSalario());

            Trabajador guardado = trabajadorRepo.save(t);
            TrabajadorDTO trabajadorDTO = trabajadorMapper.toDto(guardado);
            
            respuesta.put("mensaje", "Trabajador registrado correctamente");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.CREATED);
            respuesta.put("trabajador", trabajadorDTO);
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al registrar trabajador: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }


    @Override
    public Map<String, Object> eliminarLogico(Integer id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            Optional<Trabajador> op = trabajadorRepo.findById(id);
            if (op.isPresent()) {
                Trabajador t = op.get();
                t.setEnabled(false);
                t.setDelete(true);
                Trabajador actualizado = trabajadorRepo.save(t);
                TrabajadorDTO dto = trabajadorMapper.toDto(actualizado);
                respuesta.put("mensaje", "Trabajador eliminado lógicamente");
                respuesta.put("fecha", new Date());
                respuesta.put("status", HttpStatus.OK);
                respuesta.put("trabajador", dto);
            } else {
                respuesta.put("mensaje", "Trabajador no encontrado");
                respuesta.put("fecha", new Date());
                respuesta.put("status", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al eliminar trabajador: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> listarTodos() {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            List<Trabajador> lista = trabajadorRepo.findAll();
            respuesta.put("mensaje", "Listado de todos los trabajadores");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("trabajadores", trabajadorMapper.toDtos(lista));
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al listar trabajadores: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> listarActivos() {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            List<Trabajador> lista = trabajadorRepo.findAll().stream()
                    .filter(t -> t.isEnabled() && !t.isDelete())
                    .toList();
            respuesta.put("mensaje", "Listado de trabajadores activos");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("trabajadores", trabajadorMapper.toDtos(lista));
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al listar trabajadores activos: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> listarInactivos() {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            List<Trabajador> lista = trabajadorRepo.findAll().stream()
                    .filter(t -> !t.isEnabled())
                    .toList();
            respuesta.put("mensaje", "Listado de trabajadores inactivos");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("trabajadores", trabajadorMapper.toDtos(lista));
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al listar trabajadores inactivos: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> obtenerPorId(Integer id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            Optional<Trabajador> trabajador = trabajadorRepo.findById(id);
            if (trabajador.isPresent()) {
                respuesta.put("mensaje", "Trabajador encontrado");
                respuesta.put("fecha", new Date());
                respuesta.put("status", HttpStatus.OK);
                respuesta.put("trabajador", trabajadorMapper.toDto(trabajador.get()));
            } else {
                respuesta.put("mensaje", "Trabajador no encontrado");
                respuesta.put("fecha", new Date());
                respuesta.put("status", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al buscar trabajador: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }
}