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
