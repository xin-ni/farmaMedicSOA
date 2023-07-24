package com.example.demo.services;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.vendedorModel;
import com.example.demo.repository.vendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class vendedorService {
    @Autowired
    private vendedorRepository vendedorRepository;

    public List<vendedorModel> obtenerVendedor() {
        return (List<vendedorModel>) vendedorRepository.findAll();
    }
   
    public vendedorModel guardarVendedor(vendedorModel cargo) {
        return vendedorRepository.save(cargo);
    }

    public Optional<vendedorModel> obtenerVendedorID(int id) {
        return vendedorRepository.findById(id);
    }

    public void eliminarVendedor(int id) {
        vendedorRepository.deleteById(id);
    }

    public vendedorModel obtenerVendedorPorId(int id) {
        return vendedorRepository.findById(id).orElse(null);
    }
}
