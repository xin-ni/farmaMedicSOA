package com.example.demo.services;

import com.example.demo.models.productoModel;
import com.example.demo.repository.productoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class productoService {

    @Autowired
    private productoRepository productoRepository;

    public List<productoModel> obtenerProductos() {
        return (List<productoModel>) productoRepository.findAll();
    }

    public productoModel guardarProducto(productoModel producto) {
        return productoRepository.save(producto);
    }

    public Optional<productoModel> obtenerProductoPorId(int id) {
        return productoRepository.findById(id);
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

}
