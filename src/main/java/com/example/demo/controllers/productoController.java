package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.productoModel;
import com.example.demo.services.productoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/entity/producto")
public class productoController {
@Autowired
productoService productoService;

@GetMapping()
public ArrayList<productoModel> obtenerProducto(){
return productoService.obtenerProducto();
}

@PostMapping()
public productoModel guardarProdcuto(@RequestBody productoModel usuario){
return this.productoService.guardarProducto(usuario);
}

@GetMapping( path = "/{id}")
public Optional<productoModel> obtenerProductoPorId(@PathVariable("id") int id) {
return this.productoService.obtenerPorId(id);
}

/*@GetMapping("/query")
public ArrayList<productoModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
return this.productoService.obtenerPorPrioridad(prioridad);
}*/

@DeleteMapping( path = "/{id}")
public String eliminarPorId(@PathVariable("id") int id){
boolean ok = this.productoService.eliminarProducto(id);
if (ok){
return "Se eliminó el producto con id " + id;
}else{
return "No pudo eliminar el producto con id" + id;
}
}

}

