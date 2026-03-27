package com.ubam.dentcare_plus.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Immutable 
@Table(name = "v_citascompletas")
@AllArgsConstructor
@NoArgsConstructor
public class CitaCompletaView {

    @Id
    @Column(name = "CitaId")
    private Integer citaId;

    @Column(name = "Cita_Fecha")
    private LocalDate citaFecha;

    @Column(name = "Cita_Hora")
    private LocalTime citaHora;

    @Column(name = "Cita_CostoFinal")
    private BigDecimal citaCostoFinal;

    @Column(name = "Cita_Notas", columnDefinition = "TEXT")
    private String citaNotas;

    @Column(name = "ClienteId")
    private Integer clienteId;

    @Column(name = "nombreCliente")
    private String nombreCliente;

    @Column(name = "Cliente_Telefono")
    private String clienteTelefono;

    @Column(name = "Cliente_FechaNacimiento") 
    private LocalDate clienteFechaNacimiento;

    @Column(name = "DentistaId")
    private Integer dentistaId;

    @Column(name = "nombreDentista")
    private String nombreDentista;

    @Column(name = "Especialidad")
    private String especialidad;

    @Column(name = "Dentista_CedulaProf")
    private String dentistaCedulaProf;

    @Column(name = "Servicio_Nombre")
    private String servicioNombre;

    @Column(name = "Servicio_Descripcion", columnDefinition = "TEXT")
    private String servicioDescripcion;

    @Column(name = "Estatus_Nombre")
    private String estatusNombre;
}
