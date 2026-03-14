package com.ubam.dentcare_plus.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tbl_cat_servicios")
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServicioId")
    private Integer id;
    @Column(name = "Servicio_Nombre")
    private String nombre;
    @Column(name = "Servicio_Descripcion")
    private String descripcion;
    @Column(name = "Servicio_Precio")
    private Float precio;
    @Column(name = "Servicio_ImagenUrl")
    private String imagen;
}
