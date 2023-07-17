package com.example.demo.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.models.vendedorModel;

@Repository
public interface vendedorRepository extends CrudRepository<vendedorModel, Integer> {
    @Query("SELECT v FROM vendedorModel v WHERE v.usuario.idUsuario = ?1")
    vendedorModel findByUsuarioId(int idUsuario);    
    
}
