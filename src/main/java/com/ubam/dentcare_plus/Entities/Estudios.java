package com.ubam.dentcare_plus.entities;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_rel_estudios")
public class Estudios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EstudioId")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Estudio_HistorialId")
    private Historial historial;
    @Column(name = "Estudio_Nombre")
    private String nombre;
    @Column(name = "Estudio_RutaUrl")
    private String rutaUrl;
    @Column(name = "Estudio_Tipo")
    private String tipo;
    @Column(name = "Estudio_Fecha")
    @CreationTimestamp
    private java.time.LocalDateTime fecha;
}
