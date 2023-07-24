package com.example.demo.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.models.ventaModel;

@Repository
public interface ventaRepository extends CrudRepository<ventaModel, Integer> {
  
}
