package com.example.demo.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vendedor")
@Getter
@Setter
@ToString
public class vendedorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idVendedor;
    private String nombres;
    private String apellidos;
    private int DNI;
    private int telefono;
    private String direccion;
    private Date fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private usuarioModel usuario;

}
