package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.cargoModel;
import com.example.demo.models.categoriaModel;


@Repository
public interface cargoRepository extends CrudRepository<cargoModel, Integer> {
 List<cargoModel> findByEstado(int estado);   
}