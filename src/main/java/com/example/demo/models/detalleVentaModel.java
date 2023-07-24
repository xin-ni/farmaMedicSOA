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
@Table(name="detalleVenta")
@Getter
@Setter
@ToString
public class detalleVentaModel {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idDetalleVenta;
    @ManyToOne
    @JoinColumn(name = "idVenta")
    private ventaModel venta;
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private productoModel producto;
    private int cantidad;
    private double precioVenta;
    public void setProducto(int parseInt) {
    }
    public void setProducto(productoModel producto) {
        this.producto = producto;
    }
}
