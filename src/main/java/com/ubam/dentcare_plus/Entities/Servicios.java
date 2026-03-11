package com.ubam.dentcare_plus.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(name = "ServicioId")
    private Integer id;
    @Column(name = "Servicio_Nombre")
    private String nombre;
    @Column(name = "Servicio_Descripcion")
    private String descripcion;
    @Column(name = "Servicio_Precio")
    private Float precio;
}
