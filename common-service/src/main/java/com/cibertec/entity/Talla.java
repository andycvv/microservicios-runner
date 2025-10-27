package com.cibertec.entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_talla")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Talla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numero;
    
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
