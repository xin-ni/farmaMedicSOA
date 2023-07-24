package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.detalleVentaModel;

public interface detalleVentaRepository extends CrudRepository<detalleVentaModel, Integer> {
}