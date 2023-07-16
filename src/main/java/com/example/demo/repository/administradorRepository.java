package com.example.demo.repository;

  
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.administradorModel;


@Repository
public interface administradorRepository extends CrudRepository<administradorModel, Integer> {

}