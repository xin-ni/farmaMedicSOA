package com.example.demo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.models.pedidoModel;
import com.example.demo.repository.pedidoRepository;

@Service
public class pedidoService {

    @Autowired
    private pedidoRepository pedidoRepository;

    public List<pedidoModel> obtenerPedido() {
        return (List<pedidoModel>) pedidoRepository.findAll();
    }
   
    public pedidoModel guardarPedido(pedidoModel categoria) {
        return pedidoRepository.save(categoria);
    }

    public Optional<pedidoModel> obtenerPedidoPorId(int id) {
        return pedidoRepository.findById(id);
    }

    public void eliminarPedido(int id) {
        pedidoRepository.deleteById(id);
    }
}
