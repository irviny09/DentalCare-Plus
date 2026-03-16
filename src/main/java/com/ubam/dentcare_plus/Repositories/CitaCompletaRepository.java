package com.ubam.dentcare_plus.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ubam.dentcare_plus.dto.cliente.ActividadRecienteDTO;
import com.ubam.dentcare_plus.dto.cliente.CitaSiguienteDTO;
import com.ubam.dentcare_plus.entities.CitaCompletaView;

public interface CitaCompletaRepository extends JpaRepository<CitaCompletaView, Integer>{
    
    @Query(value = "call sp_agendaByRol(:usuarioId, :rolId)", nativeQuery = true)
    List<CitaCompletaView> getCitas(@Param("usuarioId") Integer usuarioId, @Param("rolId") Integer rolId);

    @Modifying
    @Query(value = "UPDATE tbl_rel_citas c SET c.Cita_EstatusId = :estatusId WHERE c.CitaId = :citaId", nativeQuery = true)
    void updateStatusCita(@Param("estatusId") Integer estatusId , @Param("citaId") Integer citaId);

    @Query(value = "select Cita_Fecha, Cita_CostoFinal,Servicio_Nombre,	Estatus_Nombre from v_citascompletas where ClienteId = :clienteId order by Cita_Fecha desc" , nativeQuery = true)
    List<ActividadRecienteDTO> getActivity(@Param("clienteId") Integer clienteId);

    @Query(value = "select Cita_Fecha , Cita_Hora , nombreDentista from v_citascompletas where ClienteId = :clienteId AND Cita_Fecha >= NOW() AND Estatus_Nombre != 'Cancelada' order by Cita_Fecha asc limit 1;", nativeQuery = true)
    List<CitaSiguienteDTO> getCitaSiguiente(@Param("clienteId") Integer clienteId);
}
