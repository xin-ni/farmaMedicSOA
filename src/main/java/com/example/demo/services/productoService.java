package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.productoModel;
import com.example.demo.repository.productoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class productoService {
@Autowired
productoRepository productoRepository;

public ArrayList<productoModel> obtenerProducto(){
return (ArrayList<productoModel>) productoRepository.findAll();
}

public productoModel guardarProducto(productoModel usuario){
return productoRepository.save(usuario);
}

public Optional<productoModel> obtenerPorId(int id){
return productoRepository.findById(id);
}


//public ArrayList<productoModel> obtenerPorPrioridad(Integer nombre) {
//return productoRepository.findByPrioridad(nombre);
//}

public boolean eliminarProducto(int id) {
try{
productoRepository.deleteById(id);
return true;
}catch(Exception err){
return false;
}
}



}