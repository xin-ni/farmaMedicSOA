package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.detalleVentaModel;

public interface detalleVentaRepository extends CrudRepository<detalleVentaModel, Integer> {

    // Definir una consulta personalizada para obtener los detalles de venta por el ID de venta
    @Query("SELECT d FROM detalleVentaModel d WHERE d.venta.idVenta = :idVenta")
    List<detalleVentaModel> findByVentaIdVenta(int idVenta);
}