package com.example.demo.controllers;


import com.example.demo.models.categoriaModel;
import com.example.demo.models.productoModel;
import com.example.demo.services.categoriaService;
import com.example.demo.services.productoService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/entity/productos")
public class productoController {

    @Autowired
    private productoService productoService;
    private categoriaService categoriaService;


    @GetMapping("/")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.obtenerProductos());
        model.addAttribute("nuevoProducto", new productoModel()); // Codigo importante para modal 
        return "listaProductos";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("producto", new productoModel());
        return "formularioCreacionProducto";
    }

    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute productoModel producto) {
        // Lógica de creación del producto (si es necesario)
        productoService.guardarProducto(producto);
        return "redirect:/entity/productos/";
    }

    @GetMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<productoModel> mostrarFormularioEdicion(@PathVariable int id) {
        productoModel producto = productoService.obtenerProductoPorId(id).orElse(null);
        if (producto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping("/editar")
    public String editarProducto(@RequestParam("idProducto") int id, @ModelAttribute productoModel producto) {
        // Lógica de edición del producto (si es necesario)
        producto.setIdProducto(id);
        productoService.guardarProducto(producto);
        return "redirect:/entity/productos/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id) {
        productoService.eliminarProducto(id);
        return "redirect:/entity/productos/";
    }

   @GetMapping("/vendedor")
    public String listarProducto(Model model) {
      List<productoModel> productos = productoService.obtenerProductos();

        // Ordenar la lista de productos por stock (de menor a mayor)
        Collections.sort(productos, Comparator.comparingInt(productoModel::getStock));

        model.addAttribute("productos", productos);
        model.addAttribute("nuevoProducto", new productoModel());
       
        return "listaProductosVendedor";
    }

}
