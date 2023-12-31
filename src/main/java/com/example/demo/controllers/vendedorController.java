package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;


import com.example.demo.models.vendedorModel;
import com.example.demo.services.vendedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/entity/vendedor")
public class vendedorController {

    @Autowired
    private vendedorService vendedorService;

   @GetMapping("/")
    public String listaVendedor(Model model) {
        List<vendedorModel> vendedores= vendedorService.obtenerVendedor();
        model.addAttribute("vendedor", vendedores);
        model.addAttribute("nuevoVendedor", new vendedorModel()); // Codigo importante para modal 
        return "listaVendedor";
    }

    @GetMapping("/obtener")
    @ResponseBody
    public ResponseEntity<List<vendedorModel>> obtenerVendedor() {
        List<vendedorModel> vendedores = vendedorService.obtenerVendedor();
        return new ResponseEntity<>(vendedores, HttpStatus.OK);
    }
    

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("vendedor", new vendedorModel());
        return "formularioCreacionVendedor";
    }

    @PostMapping("/guardar")
    public String guardarVendedor(@ModelAttribute vendedorModel vendedor) {
        vendedorService.guardarVendedor(vendedor);
        return "redirect:/entity/vendedor/";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Optional<vendedorModel> vendedor = vendedorService.obtenerVendedorID(id);
        vendedor.ifPresent(v -> model.addAttribute("vendedor", v));
        return "";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVendedor(@PathVariable int id) {
        vendedorService.eliminarVendedor(id);
        return "redirect:/entity/vendedor/";
    }
}
