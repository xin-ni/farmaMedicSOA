package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.detalleVentaModel;
import com.example.demo.services.detalleVentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entity/detalleVenta")
public class detalleVentaController {

    @Autowired
    private detalleVentaService  detalleVentaService;

   @GetMapping("/")
    public String listarDetalleVenta(Model model) {
        List<detalleVentaModel> detalleVenta= detalleVentaService.obtenerDetalleVenta();
        model.addAttribute("detalleVenta", detalleVenta);
        model.addAttribute("nuevoDetalleVenta", new detalleVentaModel()); // Codigo importante para modal 
        return "listaDetalleVenta";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("detalleVenta", new detalleVentaModel());
        return "/entity/detalleVenta/";
    }

    @PostMapping("/guardar")
    public String guardarDetalleVenta(@ModelAttribute detalleVentaModel detalleVenta) {
        detalleVentaService.guardarDetalleVenta(detalleVenta);
        return "redirect:/entity/detalleVenta/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Optional<detalleVentaModel> detalleVenta = detalleVentaService.obtenerDetalleVentaId(id);
        detalleVenta.ifPresent(v -> model.addAttribute("detalleVenta", v));
        return "";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDetalleVenta(@PathVariable int id) {
        detalleVentaService.eliminarDetalleVenta(id);
        return "redirect:/entity/detalleVenta/";
    }
}
