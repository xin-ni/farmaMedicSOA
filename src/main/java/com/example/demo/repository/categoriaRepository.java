package com.example.demo.repository;

import com.example.demo.models.categoriaModel;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoriaRepository extends CrudRepository<categoriaModel, Integer> {
    List<categoriaModel> findByEstado(int estado);

}
