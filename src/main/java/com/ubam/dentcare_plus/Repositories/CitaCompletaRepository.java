package com.ubam.dentcare_plus.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ubam.dentcare_plus.Entities.CitaCompletaView;

public interface CitaCompletaRepository extends JpaRepository<CitaCompletaView, Integer>{
    
    @Query(value = "call sp_agendaByRol(:usuarioId, :rolId)", nativeQuery = true)
    List<CitaCompletaView> getCitas(@Param("usuarioId") Integer usuarioId, @Param("rolId") Integer rolId);

    @Modifying
    @Query(value = "UPDATE tbl_rel_citas c SET c.Cita_EstatusId = :estatusId WHERE c.CitaId = :citaId", nativeQuery = true)
    void updateStatusCita(@Param("estatusId") Integer estatusId , @Param("citaId") Integer citaId);
}
