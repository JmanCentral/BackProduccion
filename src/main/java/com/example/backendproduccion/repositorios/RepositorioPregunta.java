package com.example.backendproduccion.repositorios;


import com.example.backendproduccion.entidades.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface RepositorioPregunta extends JpaRepository<Pregunta, Long> , JpaSpecificationExecutor<Pregunta> {

    List<Pregunta> findByCategoriaAndDificultadAndEstado(String categoria , String dificultad , boolean estado);

    List<Pregunta> findByPregunta(String pregunta);

}
