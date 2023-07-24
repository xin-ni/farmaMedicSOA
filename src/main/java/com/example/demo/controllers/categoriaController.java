package com.example.demo.controllers;

import com.example.demo.models.categoriaModel;
import com.example.demo.services.categoriaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        model.addAttribute("nuevaCategoria", new categoriaModel());
        return "listaCategorias";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("categoria", new categoriaModel());
        return "formularioCreacionCategoria";
    }

    @GetMapping("/obtener")
    @ResponseBody
    public ResponseEntity<List<categoriaModel>> obtenerCategorias() {
        List<categoriaModel> categorias = categoriaService.obtenerCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public String crearCategoria(@ModelAttribute categoriaModel categoria) {
        categoria.setEstado(1); // Por ejemplo, asumimos que el valor por defecto es 1
        categoriaService.guardarCategoria(categoria);
        return "redirect:/entity/categorias/";
    }

    @GetMapping("/editar/{id}")
    @ResponseBody // Esta anotación indica que devolvemos una respuesta JSON
    public ResponseEntity<categoriaModel> mostrarFormularioEdicion(@PathVariable int id) {
        categoriaModel categoria = categoriaService.obtenerCategoriaPorId(id).orElse(null);
        if (categoria == null) {
            // Si la categoría no existe, retornar un error
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Retornamos la categoría como respuesta JSON
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping("/editar")
    public String editarCategoria(@RequestParam("idCategoria") int id, @ModelAttribute categoriaModel categoria) {
        // Obtenemos el valor seleccionado del combo (Activado = 1, Desactivado = 0)
        int estadoSeleccionado = categoria.getEstado();

        // Convertimos el valor seleccionado del combo al valor numérico correcto (1 o 0)
        // Aquí puedes ajustar según necesites, si el combo usa otros valores distintos a 1 y 0
        if (estadoSeleccionado == 1) {
            categoria.setEstado(1); // Activado
        } else {
            categoria.setEstado(0); // Desactivado
        }

        categoria.setIdCategoria(id);
        categoriaService.guardarCategoria(categoria);
        return "redirect:/entity/categorias/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable int id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/entity/categorias/";
    }

    @GetMapping("/vendedor")
    public String listarCategoriasVendedror(Model model) {
        model.addAttribute("categorias", categoriaService.obtenerCategorias());
        model.addAttribute("nuevaCategoria", new categoriaModel()); // Codigo importante para modal
        return "listaCategoriasVendedor";
    }


    
}
