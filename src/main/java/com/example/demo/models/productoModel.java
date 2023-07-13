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
@Table(name="producto")
@Getter
@Setter
@ToString
public class productoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private double precioCompraProducto;
    private double precioVentaProducto;
    //realizar una llave secundaria
    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private categoriaModel categoria;
    //se llama a la clase
    private Date fechaVencimiento;
    private int stock;
}
