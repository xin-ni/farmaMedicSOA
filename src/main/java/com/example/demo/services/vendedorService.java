package com.example.demo.services;
import java.util.Optional;

import com.example.demo.models.vendedorModel;
import com.example.demo.repository.vendedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class vendedorService {
    @Autowired
    vendedorRepository vendedorRepository;

    public vendedorModel guardarVendedor(vendedorModel vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Optional<vendedorModel> obtenerVendedorPorId(int id) {
        return vendedorRepository.findById(id);
    }

    public vendedorModel obtenerVendedorPorIdUsuario(int idUsuario) {
        return vendedorRepository.findByUsuarioId(idUsuario);
    }
    

    public boolean eliminarVendedor(int id) {
        try {
            vendedorRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
