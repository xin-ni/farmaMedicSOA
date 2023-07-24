package com.example.demo.controllers;



import com.example.demo.models.cargoModel;
import com.example.demo.models.categoriaModel;
import com.example.demo.services.cargoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/entity/cargo")
public class cargoController {



@Autowired
    private cargoService cargoService;

    @GetMapping("/")
    public String listarCargo(Model model) {
        model.addAttribute("cargos", cargoService.obtenerCargo());
        return "listaCargo";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("categoria", new categoriaModel());
        return "creacionCargo";
    }

    @PostMapping("/crear")
    public String crearCargo(@ModelAttribute cargoModel cargo) {
        cargo.setEstado(2);
        cargoService.guardarCargo(cargo);
        return "redirect:/entity/cargo/";
    }

    @GetMapping("/editar/{id}")
    @ResponseBody // Esta anotación indica que devolvemos una respuesta JSON
    public ResponseEntity<cargoModel> mostrarFormularioEdicion(@PathVariable int id) {
        cargoModel cargo = cargoService.obtenerCargoId(id).orElse(null);
        if (cargo == null) {
            // Si la categoría no existe, retornar un error
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Retornamos la categoría como respuesta JSON
        return new ResponseEntity<>(cargo, HttpStatus.OK);
    }
    

    @PostMapping("/editar")
    public String editarCargo(@RequestParam("idCargo") int id, @ModelAttribute cargoModel cargo) {
        cargo.setIdCargo(id);
        cargoService.guardarCargo(cargo);
        return "redirect:/entity/cargo/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCargo(@PathVariable int id) {
        cargoService.eliminarCArgo(id);
        return "redirect:/entity/cargo/";
    }


}

