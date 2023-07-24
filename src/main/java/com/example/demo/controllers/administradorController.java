package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.administradorModel;
import com.example.demo.models.vendedorModel;
import com.example.demo.services.administradorService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/entity/administrador")
public class administradorController {
    @Autowired
    administradorService administradorService;

    @GetMapping()
    public ArrayList<administradorModel> obtenerAdmin() {
        return administradorService.obtenerAdmin();
    }

    @GetMapping("/obtener")
    @ResponseBody
    public ResponseEntity<List<administradorModel>> obtenerAdministrador() {
        List<administradorModel> administradores = administradorService.obtenerAdmin();
        return new ResponseEntity<>(administradores, HttpStatus.OK);
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
