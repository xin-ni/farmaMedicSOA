package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.administradorModel;
import com.example.demo.repository.administradorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class administradorService {
@Autowired
administradorRepository administradorRepository;

public ArrayList<administradorModel> obtenerAdmin(){
return (ArrayList<administradorModel>) administradorRepository.findAll();
}

public administradorModel guardarAdministrador(administradorModel usuario){
return administradorRepository.save(usuario);
}

public Optional<administradorModel> obtenerAdminId(int id){
return administradorRepository.findById(id);
}

  

public boolean eliminarAdministrador(int id) {
try{
administradorRepository.deleteById(id);
return true;
}catch(Exception err){
return false;
}
}



}