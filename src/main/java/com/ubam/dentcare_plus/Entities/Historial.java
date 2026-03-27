package com.ubam.dentcare_plus.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_ope_historial")
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HistorialId")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Historial_ClienteId")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "Historial_DentistaId")
    private Dentista dentista;
    @Column(name = "Historial_Diagnostico")
    private String diagnostico;
    @Column(name = "Historial_Tratamiento")
    private String tratamiento;
    @Column(name = "Historial_Recomendaciones")
    private String recomendaciones;
    @Column(name = "Historial_FechaCons")
    @org.hibernate.annotations.CreationTimestamp
    LocalDateTime fechaConsulta;

    @OneToMany(mappedBy = "historial")
    List<Estudios> estudios;
}
