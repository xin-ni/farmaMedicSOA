package com.example.demo.controllers;

import com.example.demo.services.usuarioService;
import com.example.demo.models.usuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/entity/usuario")
public class usuarioController {


    @Autowired
    private usuarioService usuarioService;

    // Resto de los métodos existentes en usuarioController...
    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("email", "");
        model.addAttribute("pass", "");
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("pass") String pass, Model model) {

        // Lógica para verificar las credenciales del usuario
        usuarioModel usuario = usuarioService.findByEmail(email);
        if (usuario != null && usuario.getPass().equals(pass)) {
            // Credenciales válidas, redireccionar según el idCargo
            int idCargo = usuario.getCargo().getIdCargo();
            if (idCargo == 1) {
                return "redirect:/entity/categorias/";
            } else if (idCargo == 2) {
                return "redirect:/entity/productos/vendedor";
            } else {
                // Página predeterminada o manejo de error si no se encuentra el idCargo
                return "redirect:/otraPagina";
            }
        } else {
            // Credenciales inválidas, mostrar mensaje de error en la página de inicio de sesión
              model.addAttribute("error", "Credenciales inválidas. Por favor, intente de nuevo.");
            return "login";
        }
    }
    

    @GetMapping("/")
    public String listarUsuario(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerUsuario());
        model.addAttribute("nuevaCategoria", new usuarioModel()); // Codigo importante para modal 
        return "listaUsuario";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("usuario", new usuarioModel());
        return "formularioCreacionUsuario";
    }

    @PostMapping("/crear")
    public String crearCategoria(@ModelAttribute usuarioModel usuario) {
       
        usuarioService.guardarUsuario(usuario);
        return "redirect:/entity/usuario/";
    }

   
}
