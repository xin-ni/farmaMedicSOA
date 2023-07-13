package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.categoriaModel;
import com.example.demo.services.categoriaService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/entity/categoria")
public class categoriaController {
@Autowired
categoriaService categoriaService;

@GetMapping()
public ArrayList<categoriaModel> obtenerCategoria(){
return categoriaService.obtenerCategoria();
}

@PostMapping()
public categoriaModel guardarCategoria(@RequestBody categoriaModel usuario){
return this.categoriaService.guardarCategoria(usuario);
}

@GetMapping( path = "/{id}")
public Optional<categoriaModel> obtenerCategoriaPorId(@PathVariable("id") int id) {
return this.categoriaService.obtenerPorId(id);
}

/*@GetMapping("/query")
public ArrayList<productoModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
return this.productoService.obtenerPorPrioridad(prioridad);
}*/

@DeleteMapping( path = "/{id}")
public String eliminarPorId(@PathVariable("id") int id){
boolean ok = this.categoriaService.eliminarCategoria(id);
if (ok){
return "Se eliminó el usuario con id " + id;
}else{
return "No pudo eliminar el usuario con id" + id;
}
}

}

