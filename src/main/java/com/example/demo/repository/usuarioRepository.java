package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.cargoModel;
import com.example.demo.models.usuarioModel;


@Repository
public interface usuarioRepository extends CrudRepository<usuarioModel, Integer> {
    usuarioModel findByEmail(String email);

    }