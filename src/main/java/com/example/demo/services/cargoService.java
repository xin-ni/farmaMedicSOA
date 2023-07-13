package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.cargoModel;
import com.example.demo.repository.cargoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cargoService {
@Autowired
cargoRepository cargoRepository;

public ArrayList<cargoModel> obtenerCargo(){
return (ArrayList<cargoModel>) cargoRepository.findAll();
}

public cargoModel guardarCargo(cargoModel usuario){
return cargoRepository.save(usuario);
}

public Optional<cargoModel> obtenerCargoId(int id){
return cargoRepository.findById(id);
}


//public ArrayList<productoModel> obtenerPorPrioridad(Integer nombre) {
//return productoRepository.findByPrioridad(nombre);
//}

public boolean eliminarCargo(int id) {
try{
cargoRepository.deleteById(id);
return true;
}catch(Exception err){
return false;
}
}



}