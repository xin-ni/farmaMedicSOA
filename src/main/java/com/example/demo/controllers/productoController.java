package com.example.demo.controllers;


import com.example.demo.models.productoModel;
import com.example.demo.services.categoriaService;
import com.example.demo.services.productoService;
import com.example.demo.models.categoriaModel;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;

@Controller
@RequestMapping("/entity/productos")
public class productoController {

    @Autowired
    

    private final productoService productoService;
    private final categoriaService categoriaService;

    @Autowired
    public productoController(productoService productoService, categoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    

    @GetMapping("/")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.obtenerProductos());
        return "listaProductos";
    }
    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("producto", new productoModel());
        List<categoriaModel> categoriasActivas = categoriaService.obtenerCategoriasActivas();
        model.addAttribute("categorias", categoriasActivas);
        return "formularioCreacionProducto";
    }
    

    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute productoModel producto) {
        productoService.guardarProducto(producto);
        return "redirect:/entity/productos/";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        model.addAttribute("producto", productoService.obtenerProductoPorId(id).orElse(null));
        List<categoriaModel> categoriasActivas = categoriaService.obtenerCategoriasActivas();
        model.addAttribute("categorias", categoriasActivas);
        return "formularioEdicionProducto";
    }
    
    

    @PostMapping("/editar/{id}")
    public String editarProducto(@PathVariable int id, @ModelAttribute productoModel producto) {
        producto.setIdProducto(id);
        productoService.guardarProducto(producto);
        return "redirect:/entity/productos/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id) {
        productoService.eliminarProducto(id);
        return "redirect:/entity/productos/";
    }
}

