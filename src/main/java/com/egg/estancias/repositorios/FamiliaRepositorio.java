package com.egg.estancias.repositorios;

import com.egg.estancias.entidades.Familia;
import com.egg.estancias.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepositorio extends JpaRepository<Familia, String> {
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);
    
}
