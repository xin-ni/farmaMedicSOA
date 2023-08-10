package com.example.demo.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.vendedorModel;
import com.example.demo.models.ventaModel;
import com.example.demo.models.detalleVentaModel;
import com.example.demo.models.productoModel;
import com.example.demo.services.vendedorService;
import com.example.demo.services.ventaService;

import jakarta.servlet.http.HttpServletRequest;

import com.example.demo.services.detalleVentaService;
import com.example.demo.services.productoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/entity/venta")
public class ventaController {
    @Autowired
    private productoService productoService; // 
    @Autowired
    private ventaService ventaService;
    @Autowired
    private vendedorService empleadoService; // Inyección del servicio de empleados
    @Autowired
    private detalleVentaService detalleVentaService; // Inyecta

    @GetMapping("/")
    public String listaVendedor(Model model) {
        List<ventaModel> ventas = ventaService.obtenerVenta();
        model.addAttribute("ventas", ventas);
        model.addAttribute("nuevaVenta", new ventaModel()); //aplicable para modal

        // Obtener la lista de empleados y agregarla al modelo
        List<vendedorModel> empleados = empleadoService.obtenerVendedor();
        model.addAttribute("empleados", empleados);
        
                // Obtener la lista de productos y agregarla al modelo
                List<productoModel> productos = productoService.obtenerProductos();
                model.addAttribute("productos", productos);
        return "listaVenta";
    }
    

    @GetMapping("/obtener")
    @ResponseBody
    public ResponseEntity<List<ventaModel>> obtenerUsuario() {
        List<ventaModel> ventas= ventaService.obtenerVenta();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }


    @GetMapping("/detalle/{idVenta}")
    @ResponseBody
    public ResponseEntity<List<detalleVentaModel>> obtenerDetalleVenta(@PathVariable int idVenta) {
        List<detalleVentaModel> detallesVenta = detalleVentaService.obtenerDetallesVentaPorIdVenta(idVenta);
        return ResponseEntity.ok(detallesVenta);
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
public String guardarVenta(@ModelAttribute ventaModel venta, HttpServletRequest request) {
    // Obtener la fecha actual del sistema
    Date fechaRegistro = new Date();
    // Obtener los detalles de la venta del formulario
    String[] idProductos = request.getParameterValues("idProducto");
    String[] cantidades = request.getParameterValues("cantidades");
    String[] preciosVenta = request.getParameterValues("preciosVenta");
    venta.setFechaRegistro(fechaRegistro);

    // Calcular el total de la venta
    float totalVenta = 0;
    for (int i = 0; i < idProductos.length; i++) {
        totalVenta += Float.parseFloat(preciosVenta[i]) * Integer.parseInt(cantidades[i]);
    }

    // Establecer el total de la venta en el modelo ventaModel
    venta.setTotalVenta(totalVenta);

    // Guardar la venta principal y obtener el ID de la venta recién guardada
    ventaService.guardarVenta(venta);
    //int idVentaGuardada = venta.getIdVenta();

    // Obtener el vendedor seleccionado por su id
    int idVendedorSeleccionado = Integer.parseInt(request.getParameter("trabajador"));
    vendedorModel vendedor = empleadoService.obtenerVendedorID(idVendedorSeleccionado).orElse(null);

    // Establecer el vendedor en la venta
    venta.setVendedor(vendedor);

    // Guardar los detalles de la venta en la base de datos y actualizar el stock de los productos vendidos
    for (int i = 0; i < idProductos.length; i++) {
        detalleVentaModel detalleVenta = new detalleVentaModel();

        // Establecer la venta asociada al detalleVenta
        detalleVenta.setVenta(venta);

        // Obtener el producto seleccionado por su id
        int idProductoSeleccionado = Integer.parseInt(idProductos[i]);
        productoModel producto = productoService.obtenerProductoPorId(idProductoSeleccionado).orElse(null);

        if (producto != null) {
            // Actualizar el stock del producto restando la cantidad vendida
            int cantidadVendida = Integer.parseInt(cantidades[i]);
            producto.setStock(producto.getStock() - cantidadVendida);

            // Guardar el producto actualizado en la base de datos
            productoService.guardarProducto(producto);

            detalleVenta.setProducto(producto);
            detalleVenta.setCantidad(cantidadVendida);
            detalleVenta.setPrecioVenta(Float.parseFloat(preciosVenta[i]));
            detalleVentaService.guardarDetalleVenta(detalleVenta);
        }
    }

    return "redirect:/entity/venta/";
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


     @GetMapping("/admin")
    public String listaventa(Model model) {
        List<ventaModel> ventas = ventaService.obtenerVenta();
        model.addAttribute("ventas", ventas);
        model.addAttribute("nuevaVenta", new ventaModel());

        // Obtener la lista de empleados y agregarla al modelo
        List<vendedorModel> empleados = empleadoService.obtenerVendedor();
        model.addAttribute("empleados", empleados);
        
                // Obtener la lista de productos y agregarla al modelo
                List<productoModel> productos = productoService.obtenerProductos();
                model.addAttribute("productos", productos);
        return "listaVentaAdmin";
    }
    
}
