package com.example.demo.models;

import java.sql.Date;

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
@Table(name="pedido")
@Getter
@Setter
@ToString
public class pedidoModel {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idPedido;
    //realizar una llave secundaria
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private productoModel producto;
    //se llama a la clase
    private int cantidad;
    private Date fecha;
    private String proveedor;


}
