package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.cargoModel;
import com.example.demo.models.usuarioModel;
import com.example.demo.repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class usuarioService {

    @Autowired
    private usuarioRepository usuarioRepository;

    public List<usuarioModel> obtenerUsuario() {
        return (List<usuarioModel>) usuarioRepository.findAll();
    }
   
    public usuarioModel guardarUsuario(usuarioModel cargo) {
        return usuarioRepository.save(cargo);
    }

    public Optional<usuarioModel> obtenerUsuarioId(int id) {
        return usuarioRepository.findById(id);
    }

    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }


}