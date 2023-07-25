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

  
}
