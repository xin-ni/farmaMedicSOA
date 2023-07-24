package com.example.demo.models;

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
@Table(name = "usuario")
@Getter
@Setter
@ToString
public class usuarioModel {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(unique = true, nullable = false)
     private int idUsuario;
     // realizar una llave secundaria
     @ManyToOne
     @JoinColumn(name = "idCargo")
     private cargoModel cargo;
     @Column(name = "email")
     private String email;
     private String pass;
     public static Object obtenerUsuarioId(int id) {
          return null;
     }
}
