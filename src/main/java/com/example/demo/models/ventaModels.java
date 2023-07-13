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
@Table(name="venta")
@Getter
@Setter
@ToString
public class ventaModels {
       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idVenta;
    private int idVendedor;
    private String nombreCliente;
    private Date fechaRegistro;
    private double totalVenta;
}
