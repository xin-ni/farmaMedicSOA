package com.example.demo.controllers;

import com.example.demo.models.productoModel;
import com.example.demo.services.categoriaService;
import com.example.demo.services.productoService;
import com.example.demo.models.categoriaModel;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.WebDataBinder;

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
        model.addAttribute("nuevoProducto", new productoModel());
        List<categoriaModel> categoriasActivas = categoriaService.obtenerCategoriasActivas();
        model.addAttribute("categorias", categoriasActivas);
        return "listaProductos";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("producto", new productoModel());

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



    //vista vendedor

@GetMapping("/productoStock")
    public String listarProductosVendedor(Model model) {
        List<productoModel> productos = productoService.obtenerProductos();

        // Ordenar la lista de productos por stock (de menor a mayor)
        Collections.sort(productos, Comparator.comparingInt(productoModel::getStock));

        model.addAttribute("productos", productos);
        model.addAttribute("nuevoProducto", new productoModel());
        List<categoriaModel> categoriasActivas = categoriaService.obtenerCategoriasActivas();
        model.addAttribute("categorias", categoriasActivas);
        return "listaProductosVendedor";
    }
}
