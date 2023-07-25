package com.example.demo.controllers;

import com.example.demo.models.pedidoModel;
import com.example.demo.models.productoModel;
import com.example.demo.services.pedidoService;
import com.example.demo.services.productoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entity/pedidos")
public class pedidoController {

    @Autowired
    private pedidoService pedidoService;

       @Autowired
    private productoService productoService; // Agregamos el servicio de productos

    @GetMapping("/")
    public String listarCategorias(Model model) {
        List<pedidoModel> pedidos = pedidoService.obtenerPedido();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("nuevoPedido", new pedidoModel());

        // Obtener la lista de productos y agregarla al modelo
        List<productoModel> productos = productoService.obtenerProductos();
        model.addAttribute("productos", productos);

        return "listaPedidos";
    }

    @GetMapping("/obtener")
    @ResponseBody
    public ResponseEntity<List<pedidoModel>> obtenerPedido() {
        List<pedidoModel> ventas= pedidoService.obtenerPedido();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }


    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("pedido", new pedidoModel());
        return "formularioCreaciónPedido";
    }

    @PostMapping("/crear")
    public String crearPedido(@ModelAttribute pedidoModel pedido) {

         // Obtener el producto seleccionado en el pedido por su ID
    productoModel producto = productoService.obtenerProductoPorId(pedido.getProducto().getIdProducto()).orElse(null);

    if (producto != null) {
        // Obtener la cantidad ingresada en el pedido
        int cantidadPedido = pedido.getCantidad();

        // Actualizar el stock del producto sumándole la cantidad del pedido
        producto.setStock(producto.getStock() + cantidadPedido);

        // Guardar el producto actualizado en la base de datos
        productoService.guardarProducto(producto);
    }

        pedidoService.guardarPedido(pedido);
        return "redirect:/entity/pedidos/";
    }

    @GetMapping("/editar/{id}")
@ResponseBody
public ResponseEntity<pedidoModel> mostrarFormularioEdicion(@PathVariable int id) {
    pedidoModel pedido = pedidoService.obtenerPedidoPorId(id).orElse(null);
    if (pedido == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(pedido, HttpStatus.OK);
}
    

@PostMapping("/editar")
public String editarPedido(@RequestParam("idPedido") int id, @ModelAttribute pedidoModel pedido) {
    pedido.setIdPedido(id);
    pedidoService.guardarPedido(pedido);
    return "redirect:/entity/pedidos/";
}

    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable int id) {
        pedidoService.eliminarPedido(id);
        return "redirect:/entity/pedidos/";
    }


    
}