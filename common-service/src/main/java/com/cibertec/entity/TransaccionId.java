package com.cibertec.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class TransaccionId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_producto", nullable = false)
	private Integer idProducto;
	
	@Column(name = "id_boleta", nullable = false)
	private Integer idBoleta;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        TransaccionId idTrs = (TransaccionId) o;
        return Objects.equals(idBoleta, idTrs.idBoleta) && Objects.equals(idProducto, idTrs.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idBoleta);
    }
}
