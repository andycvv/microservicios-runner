package com.cibertec.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_calzado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calzado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer stock;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_talla")
    private Talla talla;
    
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(updatable = true)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean isDelete = false;

    @Column(nullable = false)
    private boolean isEnabled = true;
}
