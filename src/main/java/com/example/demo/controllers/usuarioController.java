package com.example.demo.controllers;


import com.example.demo.models.usuarioModel;
import com.example.demo.services.usuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entity/usuario")

public class usuarioController {

    @Autowired
    private usuarioService usuarioService;

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // Nombre de la plantilla Thymeleaf para el formulario de login
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email, @RequestParam String pass) {
        // Aquí realizarás la autenticación con el usuario y contraseña ingresados
        usuarioModel usuario = usuarioService.findByEmail(email);
        if (usuario != null && usuario.getPass().equals(pass)) {
            // Si el usuario y contraseña son válidos, redirigir según el idCargo
            int idCargo = usuario.getCargo().getIdCargo();
            if (idCargo == 1) {
                return "redirect:/entity/productos/";
            } else if (idCargo == 0) {
                return "redirect:/entity/categorias/";
            }
        }
        // Si la autenticación falla, redirigir nuevamente al formulario de login con un mensaje de error
        return "redirect:/login?error";
    }
}

