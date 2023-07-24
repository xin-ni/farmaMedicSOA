package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ventaModel;
import com.example.demo.repository.ventaRepository;

@Service
public class ventaService {
        @Autowired
    private ventaRepository ventaRepository;

    public List<ventaModel> obtenerVenta() {
        return (List<ventaModel>) ventaRepository.findAll();
    }

    public ventaModel guardarVenta(ventaModel categoria) {
        return ventaRepository.save(categoria);
    }

    public Optional<ventaModel> obtenerVentaId(int id) {
        return ventaRepository.findById(id);
    }

    public void eliminarVenta(int id) {
        ventaRepository.deleteById(id);
    }
}
