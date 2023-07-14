package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.usuarioModel;


@Repository
public interface usuarioRepository extends CrudRepository<usuarioModel, Integer> {
    usuarioModel findByEmail(String email);
    }