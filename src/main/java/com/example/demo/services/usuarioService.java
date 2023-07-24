package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.cargoModel;
import com.example.demo.models.usuarioModel;
import com.example.demo.repository.administradorRepository;
import com.example.demo.repository.usuarioRepository;
import com.example.demo.repository.vendedorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.administradorModel;
import com.example.demo.models.vendedorModel;

@Service
public class usuarioService {

    private final administradorRepository administradorRepo;
    private final vendedorRepository vendedorRepo;
    private final Logger logger = LoggerFactory.getLogger(usuarioService.class);

    @Autowired
    public usuarioService(administradorRepository administradorRepo, vendedorRepository vendedorRepo) {
        this.administradorRepo = administradorRepo;
        this.vendedorRepo = vendedorRepo;
    }

    @Autowired
    private usuarioRepository usuarioRepository;

    public usuarioModel obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

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

    public usuarioModel findByEmail(String email) {
        logger.info("Buscando usuario por email: {}", email);
        usuarioModel usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            logger.info("Usuario encontrado. ID de usuario: {}", usuario.getIdUsuario());
        } else {
            logger.warn("Usuario no encontrado para el email: {}", email);
        }
        return usuario;
    }

    public String obtenerNombreAdministradorPorEmail(String email) {
        logger.info("Buscando usuario por email: {}", email);
        usuarioModel usuario = usuarioRepository.findByEmail(email);
    
        if (usuario != null) {
            administradorModel administrador = administradorRepo.findByUsuarioId(usuario.getIdUsuario());
            if (administrador != null) {
                logger.info("Nombre del administrador encontrado: {}", administrador.getNombres());
                return administrador.getNombres();
            } else {
                logger.warn("Administrador no encontrado para el email: {}", email);
                return ""; // Retorna una cadena vacía si no se encuentra el administrador
            }
        } else {
            logger.warn("Usuario no encontrado para el email: {}", email);
            return ""; // Retorna una cadena vacía si no se encuentra el usuario
        }
    }
    
    
    public String obtenerNombreVendedorPorEmail(String email) {
        logger.info("Buscando usuario por email: {}", email);
        usuarioModel usuario = usuarioRepository.findByEmail(email);
    
        if (usuario != null) {
            vendedorModel vendedor = vendedorRepo.findByUsuario(usuario);
            if (vendedor != null) {
                logger.info("Nombre del vendedor encontrado: {}", vendedor.getNombres());
                return vendedor.getNombres();
            } else {
                logger.warn("Vendedor no encontrado para el email: {}", email);
                return ""; // Retorna una cadena vacía si no se encuentra el vendedor
            }
        } else {
            logger.warn("Usuario no encontrado para el email: {}", email);
            return ""; // Retorna una cadena vacía si no se encuentra el usuario
        }
    }
    
}
