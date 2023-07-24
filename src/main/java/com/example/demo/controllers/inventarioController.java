package com.example.demo.controllers;

import com.example.demo.models.detalleVentaModel;
import com.example.demo.models.pedidoModel;
import com.example.demo.services.detalleVentaService;
import com.example.demo.services.pedidoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bss/inventario")
public class inventarioController {

    @Autowired
    private detalleVentaService detalleVentaService;

    @Autowired
    private pedidoService pedidoService;

    @GetMapping("/")
    public String listarCategorias(Model model) {
        // Obtener la lista de detalles de venta y agregarla al modelo
        List<detalleVentaModel> detallesVenta = detalleVentaService.obtenerDetalleVenta();
        model.addAttribute("detallesVenta", detallesVenta);

        // Obtener la lista de pedidos y agregarla al modelo
        List<pedidoModel> pedidos = pedidoService.obtenerPedido();
        model.addAttribute("pedidos", pedidos);

        return "listaInventario";
    }
}