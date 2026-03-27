package com.ubam.dentcare_plus.entities;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_rel_citas")
public class Citas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CitaId")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Cita_ClienteId")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "Cita_DentistaId")
    private Dentista dentista;
    @ManyToOne
    @JoinColumn(name = "Cita_ServicioId")
    private Servicios servicios;
    @ManyToOne
    @JoinColumn(name = "Cita_EstatusId")
    private Estatus estatus;
    @JsonFormat(pattern = "H:mm[:ss]")
    @Column(name = "Cita_Fecha")
    private LocalDate citaFecha;
    @Column(name = "Cita_Hora")
    private LocalTime hora;
    @Column(name = "Cita_CostoFinal")
    private Float costoFinal;
    @Column(name = "Cita_Notas")
    private String notas;
}
