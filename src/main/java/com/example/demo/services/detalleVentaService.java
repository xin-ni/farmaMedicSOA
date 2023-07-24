package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.detalleVentaModel;
import com.example.demo.repository.detalleVentaRepository;
@Service
public class detalleVentaService {
    private final detalleVentaRepository detalleVentaRepository;

    @Autowired
    public detalleVentaService(detalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    public List<detalleVentaModel> obtenerDetalleVenta() {
        return (List<detalleVentaModel>) detalleVentaRepository.findAll();
    }

    public detalleVentaModel guardarDetalleVenta(detalleVentaModel detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    public Optional<detalleVentaModel> obtenerDetalleVentaId(int id) {
        return detalleVentaRepository.findById(id);
    }

    public void eliminarDetalleVenta(int id) {
        detalleVentaRepository.deleteById(id);
    }
}

