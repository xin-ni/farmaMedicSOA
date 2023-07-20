package com.example.demo.services;

import com.example.demo.models.categoriaModel;
import com.example.demo.repository.categoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class categoriaService {

    @Autowired
    private categoriaRepository categoriaRepository;

    public List<categoriaModel> obtenerCategorias() {
        return (List<categoriaModel>) categoriaRepository.findAll();
    }

    public categoriaModel guardarCategoria(categoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<categoriaModel> obtenerCategoriaPorId(int id) {
        return categoriaRepository.findById(id);
    }

    public void eliminarCategoria(int id) {
        categoriaRepository.deleteById(id);
    }
}
