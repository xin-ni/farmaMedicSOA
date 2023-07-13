package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.categoriaModel;
import com.example.demo.repository.categoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class categoriaService {
@Autowired
categoriaRepository categoriaRepository;

public ArrayList<categoriaModel> obtenerCategoria(){
return (ArrayList<categoriaModel>) categoriaRepository.findAll();
}

public categoriaModel guardarCategoria(categoriaModel usuario){
return categoriaRepository.save(usuario);
}

public Optional<categoriaModel> obtenerPorId(int id){
return categoriaRepository.findById(id);
}


//public ArrayList<productoModel> obtenerPorPrioridad(Integer nombre) {
//return productoRepository.findByPrioridad(nombre);
//}

public boolean eliminarCategoria(int id) {
try{
categoriaRepository.deleteById(id);
return true;
}catch(Exception err){
return false;
}
}



}