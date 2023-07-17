package com.example.demo.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.administradorModel;
import com.example.demo.models.usuarioModel;
import com.example.demo.models.vendedorModel;
import com.example.demo.services.usuarioService;
import com.example.demo.services.vendedorService;
import com.example.demo.services.administradorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/entity/usuario")
public class usuarioController {
@Autowired
usuarioService usuarioService;


@Autowired
administradorService administradorService; 

@Autowired
vendedorService vendedorService; // Agrega esta línea
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

@PostMapping("/login")
public RedirectView login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
    // Buscar al usuario por correo electrónico
    usuarioModel usuario = usuarioService.obtenerUsuarioPorEmail(email);

    
    if (usuario != null && usuario.getPass().equals(password)) {
        // Credenciales válidas, inicio de sesión exitoso
        int idCargo = usuario.getCargo().getIdCargo();

        if (idCargo == 1) {

            administradorModel administrador = administradorService.obtenerAdministradorPorIdUsuario(usuario.getIdUsuario());
            if (administrador != null) {
                // Agregar el nombre del vendedor como un atributo en las redirectAttributes
                redirectAttributes.addAttribute("nombreAdministrador", administrador.getNombres());
            }
            return new RedirectView("/paginas/vistas/administrador/ventas.html");
        } else {
            // Obtener el nombre del vendedor a partir del idUsuario
            vendedorModel vendedor = vendedorService.obtenerVendedorPorIdUsuario(usuario.getIdUsuario());

            if (vendedor != null) {
                // Agregar el nombre del vendedor como un atributo en las redirectAttributes
                redirectAttributes.addAttribute("nombreVendedor", vendedor.getNombres());
            }

            // Redirigir a la página del vendedor
            return new RedirectView("/paginas/vistas/vendedor/vendedor.html");
        }
    } else {
        // Credenciales inválidas, inicio de sesión fallido
        return new RedirectView("/paginas/vistas/login.html");
    }
}




}
