package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.usuarioModel;
import com.example.demo.services.usuarioService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/entity/usuario")
public class usuarioController {
@Autowired
usuarioService usuarioService;

@GetMapping()
public ArrayList<usuarioModel> obtenerUsuario(){
return usuarioService.obtenerUsuario();
}

@PostMapping()
public usuarioModel guardarCargo(@RequestBody usuarioModel usuario){
return this.usuarioService.guardarUsuario(usuario);
}

@GetMapping( path = "/{id}")
public Optional<usuarioModel> obtenerCargoId(@PathVariable("id") int id) {
return this.usuarioService.obtenerUsuarioId(id);
}

/*@GetMapping("/query")
public ArrayList<productoModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
return this.productoService.obtenerPorPrioridad(prioridad);
}*/

@DeleteMapping( path = "/{id}")
public String eliminarPorId(@PathVariable("id") int id){
boolean ok = this.usuarioService.eliminarUsuario(id);
if (ok){
return "Se eliminó el usuario con id " + id;
}else{
return "No pudo eliminar el usuario con id" + id;
}
}

}

