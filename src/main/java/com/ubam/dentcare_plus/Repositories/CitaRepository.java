package com.ubam.dentcare_plus.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ubam.dentcare_plus.dto.cliente.HorasOcupadasDTO;
import com.ubam.dentcare_plus.entities.Citas;

public interface CitaRepository extends JpaRepository<Citas, Integer> {

    @Query(value = "select Cita_Hora from v_citascompletas where Cita_Fecha = :fecha and Estatus_Nombre not in ('Cancelada') and DentistaId = :dentistaId", nativeQuery = true)
    List<HorasOcupadasDTO> showOcupado(
            @Param("fecha") LocalDate fecha,
            @Param("dentistaId") Integer dentistaId
    );
}
