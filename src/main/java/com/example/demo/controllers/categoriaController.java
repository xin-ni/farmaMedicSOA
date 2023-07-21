package com.example.demo.controllers;


import com.example.demo.models.categoriaModel;
import com.example.demo.services.categoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entity/categorias")
public class categoriaController {

    @Autowired
    private categoriaService categoriaService;

    @GetMapping("/")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.obtenerCategorias());
        model.addAttribute("nuevaCategoria", new categoriaModel()); // Codigo importante para modal 
        return "listaCategorias";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("categoria", new categoriaModel());
        return "formularioCreacionCategoria";
    }

    @PostMapping("/crear")
    public String crearCategoria(@ModelAttribute categoriaModel categoria) {
        categoria.setEstado(1); // Por ejemplo, asumimos que el valor por defecto es 1
        categoriaService.guardarCategoria(categoria);
        return "redirect:/entity/categorias/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        model.addAttribute("categoria", categoriaService.obtenerCategoriaPorId(id).orElse(null));
        return "formularioEdicionCategoria";
    }

    @PostMapping("/editar/{id}")
    public String editarCategoria(@PathVariable int id, @ModelAttribute categoriaModel categoria) {
        categoria.setIdCategoria(id);
        categoriaService.guardarCategoria(categoria);
        return "redirect:/entity/categorias/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable int id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/entity/categorias/";
    }
}
