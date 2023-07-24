package com.example.demo.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.demo.models.vendedorModel;
import com.example.demo.models.usuarioModel;

@Repository
public interface vendedorRepository extends CrudRepository<vendedorModel, Integer> {
    @Query("SELECT v FROM vendedorModel v WHERE v.usuario.email = ?1")
    vendedorModel findByUsuarioEmail(String email);
    @Query("SELECT v FROM vendedorModel v WHERE v.usuario = :usuario")
    vendedorModel findByUsuario(@Param("usuario") usuarioModel usuario);
    

}
