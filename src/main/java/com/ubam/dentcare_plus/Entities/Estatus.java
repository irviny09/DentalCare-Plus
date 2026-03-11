package com.ubam.dentcare_plus.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_cat_citas_estatus")
public class Estatus {
    @Id
    @Column(name = "EstatusId")
    private Integer id;
    private Integer nombre;
}
