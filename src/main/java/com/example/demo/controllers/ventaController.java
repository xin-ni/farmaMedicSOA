package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;


import com.example.demo.models.ventaModel;
import com.example.demo.services.ventaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entity/venta")
public class ventaController {

    @Autowired
    private ventaService ventaService;

    @GetMapping("/")
    public String listaVendedor(Model model) {
        List<ventaModel> ventas = ventaService.obtenerVenta();
        model.addAttribute("ventas", ventas);
        model.addAttribute("nuevaVenta", new ventaModel()); // Codigo importante para modal 
        return "listaVenta";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("venta", new ventaModel());
        return "";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute ventaModel venta) {
        ventaService.guardarVenta(venta);
        return "redirect:/bss/venta/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Optional<ventaModel> venta = ventaService.obtenerVentaId(id);
        venta.ifPresent(v -> model.addAttribute("venta", v));
        return "";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable int id) {
        ventaService.eliminarVenta(id);
        return "redirect:/entity/vendedor/";
    }
}
