package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.categoriaModel;

@Repository
public interface categoriaRepository extends CrudRepository<categoriaModel, Integer> {

    static ArrayList<categoriaModel> getCategoria() {
        return null;
    }
    }