package com.example.demo.repository;

  
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.administradorModel;


@Repository
public interface administradorRepository extends CrudRepository<administradorModel, Integer> {
    @Query("SELECT a FROM administradorModel a WHERE a.usuario.idUsuario = ?1")
    administradorModel findByUsuarioId(int idUsuario);    
}