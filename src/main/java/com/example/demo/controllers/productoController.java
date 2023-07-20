package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.productoModel;
import com.example.demo.services.productoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


@RestController
@RequestMapping("/entity/producto")
public class productoController {

    private final productoService productoService;

    @Autowired
    public productoController(productoService productoService) {
        this.productoService = productoService;
    }

@GetMapping()
public ArrayList<productoModel> obtenerProducto(){
return productoService.obtenerProducto();
}

 @PostMapping("/nuevo")
    public productoModel guardarProducto(@RequestBody productoModel producto) {
        return productoService.guardarProducto(producto);
    }

@GetMapping( path = "/{id}")
public Optional<productoModel> obtenerProductoPorId(@PathVariable("id") int id) {
return this.productoService.obtenerPorId(id);
}

@DeleteMapping( path = "/{id}")
public String eliminarPorId(@PathVariable("id") int id){
boolean ok = this.productoService.eliminarProducto(id);
if (ok){
return "Se eliminó el producto con id " + id;
}else{
return "No pudo eliminar el producto con id" + id;
}
}



@GetMapping("/listarPorId")
@ResponseBody // Agregamos esta anotación
public String generarTablaProducto() {
    StringBuilder tablaProductos = new StringBuilder();

ArrayList<productoModel> productos = productoService.obtenerProducto();
   Collections.sort(productos, new Comparator<productoModel>() {
    @Override
    public int compare(productoModel p1, productoModel p2) {
        return Integer.compare(p2.getIdProducto(), p1.getIdProducto());
    }
});

// Generar la tabla con los productos ordenados
for (productoModel producto : productos) {
    tablaProductos.append("<tr>");
    tablaProductos.append("<td>").append(producto.getIdProducto()).append("</td>");
    tablaProductos.append("<td>").append(producto.getNombreProducto()).append("</td>");
    tablaProductos.append("<td>").append(producto.getDescripcionProducto()).append("</td>");
    tablaProductos.append("<td>").append("S/").append(formatoMoneda(producto.getPrecioCompraProducto())).append("</td>");
    tablaProductos.append("<td>").append("S/").append(formatoMoneda(producto.getPrecioVentaProducto())).append("</td>");
    tablaProductos.append("<td>").append(producto.getCategoria().getNombreCategoria()).append("</td>");
    tablaProductos.append("<td>").append(producto.getFechaVencimiento()).append("</td>");
    tablaProductos.append("<td>").append(producto.getStock()).append("</td>");
    tablaProductos.append("<td>"+"<img alt='borrar'><img alt='editar'></td>");
    tablaProductos.append("</tr>");
}
    tablaProductos.append("</tbody>");
    tablaProductos.append("</table>");

    return tablaProductos.toString();
}
private String formatoMoneda(double valor) {
    return String.format("%.2f", valor);
}

}

