package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;


import com.example.demo.models.usuarioModel;
import com.example.demo.repository.usuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class usuarioService {
@Autowired
usuarioRepository usuarioRepository;

public ArrayList<usuarioModel> obtenerUsuario(){
return (ArrayList<usuarioModel>) usuarioRepository.findAll();
}

public usuarioModel guardarUsuario(usuarioModel usuario){
return usuarioRepository.save(usuario);
}

public Optional<usuarioModel> obtenerUsuarioId(int id){
return usuarioRepository.findById(id);
}


//public ArrayList<productoModel> obtenerPorPrioridad(Integer nombre) {
//return productoRepository.findByPrioridad(nombre);
//}
public usuarioModel obtenerUsuarioPorEmail(String email) {
    return usuarioRepository.findByEmail(email);
}

public boolean eliminarUsuario(int id) {
try{
usuarioRepository.deleteById(id);
return true;
}catch(Exception err){
return false;
}
}



}