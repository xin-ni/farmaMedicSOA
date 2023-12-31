package com.example.demo.controllers;

import com.example.demo.services.usuarioService;
import com.example.demo.models.usuarioModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/obtener")
    @ResponseBody
    public ResponseEntity<List<usuarioModel>> obtenerUsuario() {
        List<usuarioModel> usuarios= usuarioService.obtenerUsuario();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping("/login")
public String login(@RequestParam("email") String email, @RequestParam("pass") String pass, Model model) {
    // Lógica para verificar las credenciales del usuario
    usuarioModel usuario = usuarioService.findByEmail(email);
    if (usuario != null && usuario.getPass().equals(pass)) {
        // Credenciales válidas, redireccionar según el idCargo
        int idCargo = usuario.getCargo().getIdCargo();
        if (idCargo == 1) {
            // Obtener el nombre del administrador desde la tabla correspondiente
           // Controlador

    String nombreAdministrador = usuarioService.obtenerNombreAdministradorPorEmail(email);
    model.addAttribute("nombreUsuario", nombreAdministrador);
    System.out.println("Nombre de administrador: " + nombreAdministrador); // Agrega esta línea para imprimir el nombre del administrador en la consola
    return "redirect:/entity/venta/admin";


        } else if (idCargo == 2) {
            // Obtener el nombre del vendedor desde la tabla correspondiente
            String nombreVendedor = usuarioService.obtenerNombreVendedorPorEmail(email);
            model.addAttribute("nombreUsuario", nombreVendedor);
            System.out.println("Nombre de vendedor: " + nombreVendedor); // Agrega esta línea para imprimir el nombre del vendedor en la consola
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
