package com.example.backendproduccion.repositorios;


import com.example.backendproduccion.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> , JpaSpecificationExecutor<Usuario> {

    Optional<Usuario> findByUsernameAndPassword(String username, String password);

    Optional<Usuario> findByUsername(String username);

}


