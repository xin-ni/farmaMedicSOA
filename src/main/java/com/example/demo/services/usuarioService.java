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
    private usuarioRepository usuarioRepository;

    public usuarioModel findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
