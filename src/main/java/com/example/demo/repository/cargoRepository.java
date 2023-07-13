package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.cargoModel;


@Repository
public interface cargoRepository extends CrudRepository<cargoModel, Integer> {
    }