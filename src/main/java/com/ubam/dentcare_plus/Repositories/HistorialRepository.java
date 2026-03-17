package com.ubam.dentcare_plus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ubam.dentcare_plus.entities.Historial;

public interface HistorialRepository extends JpaRepository<Historial, Integer>{
    @Query("SELECT h FROM Historial h " +
           "LEFT JOIN FETCH h.estudios " +
           "WHERE h.cliente.id = :id " +
           "ORDER BY h.fechaConsulta DESC")
    List<Historial> findHistorialConEstudios(@Param("id") Integer id);
}
