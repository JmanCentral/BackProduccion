package com.example.backendproduccion.repositorios;


import com.example.backendproduccion.entidades.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioHistorial extends JpaRepository<Historial, Long> , JpaSpecificationExecutor<Historial> {

    @Query("SELECT h FROM Historial h WHERE h.usuario.username = :username ORDER BY h.tiempo DESC")
    List<Historial> getDatosDelUsuario(@Param("username") String username);

    @Query("SELECT SUM(h.puntaje) FROM Historial h WHERE h.usuario.username = :username")
    Integer getTotalPuntosDelUsuario(@Param("username") String username);

    @Query("SELECT SUM(h.ayudas) FROM Historial h WHERE h.usuario.username = :username")
    Integer getTotalAyudasDelUsuario(@Param("username") String username);

    @Query("SELECT SUM(h.tiempo) FROM Historial h WHERE h.usuario.username = :username")
    Integer getTiempoTotalDelUsuario(@Param("username") String username);

    @Query("SELECT h.pregunta.dificultad, h.pregunta.categoria FROM Historial h WHERE h.usuario.username = :username")
    List<Object[]> getDificultadYCategoriaPorUsuario(@Param("username") String username);

}
