package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.administradorModel;
import com.example.demo.services.administradorService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/entity/administrador")
public class administradorController {
    @Autowired
    administradorService administradorService;

    @GetMapping()
    public ArrayList<administradorModel> obtenerAdmin() {
        return administradorService.obtenerAdmin();
    }

    @PostMapping()
    public administradorModel guardarAdministrador(@RequestBody administradorModel admin) {
        return this.administradorService.guardarAdministrador(admin);
    }

    @GetMapping(path = "/{id}")
    public Optional<administradorModel> obtenerAdminId(@PathVariable("id") int id) {
        return this.administradorService.obtenerAdminId(id);
    }



    /*@GetMapping("/query")
    public ArrayList<productoModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
    return this.productoService.obtenerPorPrioridad(prioridad);
    }*/

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") int id) {
        boolean ok = this.administradorService.eliminarAdministrador(id);
        if (ok) {
            return "Se eliminó el usuario con id " + id;
        } else {
            return "No pudo eliminar el usuario con id " + id;
        }
    }
}
