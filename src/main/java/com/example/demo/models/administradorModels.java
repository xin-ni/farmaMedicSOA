package com.example.demo.models;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="administrador")
@Getter
@Setter
@ToString
public class administradorModels {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idAdministrador;
    private String nombres;
    private String apellidos;
    private int DNI;
    private int telefono;
    private String direccion;
    private Date fechaCreacion;
}
