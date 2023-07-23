package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.cargoModel;
import com.example.demo.services.cargoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/entity/cargo")
public class cargoController {
@Autowired
cargoService cargoService;

@GetMapping()
public ArrayList<cargoModel> obtenerCargo(){
return cargoService.obtenerCargo();
}

@PostMapping()
public cargoModel guardarCargo(@RequestBody cargoModel usuario){
return this.cargoService.guardarCargo(usuario);
}

@GetMapping( path = "/{id}")
public Optional<cargoModel> obtenerCargoId(@PathVariable("id") int id) {
return this.cargoService.obtenerCargoId(id);
}

/*@GetMapping("/query")
public ArrayList<productoModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
return this.productoService.obtenerPorPrioridad(prioridad);
}*/

@DeleteMapping( path = "/{id}")
public String eliminarPorId(@PathVariable("id") int id){
boolean ok = this.cargoService.eliminarCargo(id);
if (ok){
return "Se eliminó el usuario con id " + id;
}else{
return "No pudo eliminar el usuario con id" + id;
}
}

}

