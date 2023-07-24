package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.vendedorModel;
import com.example.demo.models.ventaModel;
import com.example.demo.models.productoModel;
import com.example.demo.services.vendedorService;
import com.example.demo.services.ventaService;
import com.example.demo.services.productoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entity/venta")
public class ventaController {
    @Autowired
    private productoService productoService; // 
    @Autowired
    private ventaService ventaService;
    @Autowired
    private vendedorService empleadoService; // Inyección del servicio de empleados


    @GetMapping("/")
    public String listaVendedor(Model model) {
        List<ventaModel> ventas = ventaService.obtenerVenta();
        model.addAttribute("ventas", ventas);
        model.addAttribute("nuevaVenta", new ventaModel());

        // Obtener la lista de empleados y agregarla al modelo
        List<vendedorModel> empleados = empleadoService.obtenerVendedor();
        model.addAttribute("empleados", empleados);
        
                // Obtener la lista de productos y agregarla al modelo
                List<productoModel> productos = productoService.obtenerProductos();
                model.addAttribute("productos", productos);
        return "listaVenta";
    }
 
    @GetMapping("/listaVentas")
    public String mostrarListaVentas(Model model) {
        List<ventaModel> ventas = ventaService.obtenerVenta();
        model.addAttribute("ventas", ventas);

        // Obtener la lista de productos y agregarla al modelo
        List<productoModel> productos = productoService.obtenerProductos();
        model.addAttribute("productos", productos);

        return "listaVenta"; // Vista para la lista de ventas
    }
    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("venta", new ventaModel());

        // Obtener la lista de empleados y agregarla al modelo
        List<vendedorModel> empleados = empleadoService.obtenerVendedor();
        model.addAttribute("empleados", empleados);

            // Obtener la lista de productos y agregarla al modelo
        List<productoModel> productos = productoService.obtenerProductos();
        model.addAttribute("productos", productos);
        return "formularioVenta"; // Vista para el formulario de registro de ventas
    }
    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute ventaModel venta) {
        ventaService.guardarVenta(venta);
        return "redirect:/bss/venta/";
        
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Optional<ventaModel> venta = ventaService.obtenerVentaId(id);
        venta.ifPresent(v -> model.addAttribute("venta", v));
        return "";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable int id) {
        ventaService.eliminarVenta(id);
        return "redirect:/entity/vendedor/";
    }
}
