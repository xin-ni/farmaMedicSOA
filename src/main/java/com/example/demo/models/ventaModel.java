package com.example.demo.models;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="venta")
@Getter
@Setter
@ToString
public class ventaModel {
       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idVenta;
     @ManyToOne
    @JoinColumn(name = "idVendedor")
    private vendedorModel vendedor;
    private String nombreCliente;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegistro;
    private double totalVenta;

    @Transient
    private String trabajador; // Campo adicional para almacenar el nomb
    
    @Transient
    private String producto; // Campo adicional para almacenar el nombre del producto relacionado con la venta

}
