package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.productoModel;

@Repository
public interface productoRepository extends CrudRepository<productoModel, Integer> {

    static productoModel findByNombreProducto(String nombreProducto) {
        return null;
    }

   // Agregar la consulta personalizada para obtener productos activos por ID y con stock mayor a 0
   List<productoModel> findByStockGreaterThanAndIdProducto(int idProducto, int stock);
}
