package com.ubam.dentcare_plus.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ubam.dentcare_plus.dto.cliente.HorasOcupadasDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasCanceladasDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasPendientesDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasPorDiaDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasPorMesDTO;
import com.ubam.dentcare_plus.entities.Citas;

public interface CitaRepository extends JpaRepository<Citas, Integer> {

    @Query(value = "select Cita_Hora from v_citascompletas where Cita_Fecha = :fecha and Estatus_Nombre not in ('Cancelada') and DentistaId = :dentistaId", nativeQuery = true)
    List<HorasOcupadasDTO> showOcupado(
            @Param("fecha") LocalDate fecha,
            @Param("dentistaId") Integer dentistaId);

    @Query(value = """
            call sp_showCitasByMonth(:dentistaId)
            """, nativeQuery = true)
    CitasPorMesDTO getCitasPorMes(@Param("dentistaId") int dentistaId);

    @Query(value = """
            SELECT
            COUNT(trc.CitaId) AS total,
            SUM(CASE WHEN trc.Cita_EstatusId = 2 THEN 1 ELSE 0 END) AS pendientes
            FROM tbl_rel_citas trc
            WHERE trc.Cita_DentistaId = :dentistaId
            AND DATE(trc.Cita_Fecha) = CURDATE();
            """, nativeQuery = true)
    CitasPorDiaDTO getCitasPorDia(@Param("dentistaId") int dentistaId);

    @Query(value = """
            SELECT
            SUM(CASE WHEN trc.Cita_EstatusId = 4 THEN 1 ELSE 0 END) AS canceladas
            FROM tbl_rel_citas trc
            WHERE trc.Cita_DentistaId = :dentistaId
            AND DATE(trc.Cita_Fecha) = CURDATE();
            """, nativeQuery = true)
    CitasCanceladasDTO getCitasCanceladas(@Param("dentistaId") int dentistaId);

    @Query(value = """
            SELECT
            SUM(CASE WHEN trc.Cita_EstatusId = 1 THEN 1 ELSE 0 END) AS pendientes
            FROM tbl_rel_citas trc
            WHERE trc.Cita_DentistaId = :dentistaId
            AND DATE(trc.Cita_Fecha) = CURDATE() + 1;
            """, nativeQuery = true)
    CitasPendientesDTO getCitasPendientes(@Param("dentistaId") int dentistaId);
}
