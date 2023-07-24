package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.cargoModel;
import com.example.demo.models.categoriaModel;
import com.example.demo.repository.cargoRepository;
import com.example.demo.repository.categoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cargoService {


  @Autowired
    private cargoRepository cargoRepository;

    public List<cargoModel> obtenerCargo() {
        return (List<cargoModel>) cargoRepository.findAll();
    }

    public cargoModel guardarCargo(cargoModel categoria) {
        return cargoRepository.save(categoria);
    }

    public Optional<cargoModel> obtenerCargoId(int id) {
        return cargoRepository.findById(id);
    }

    public void eliminarCArgo(int id) {
        cargoRepository.deleteById(id);
    }
}



