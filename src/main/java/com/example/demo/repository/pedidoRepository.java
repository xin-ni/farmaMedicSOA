package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.pedidoModel;



@Repository
public interface pedidoRepository extends CrudRepository<pedidoModel, Integer> {
}
