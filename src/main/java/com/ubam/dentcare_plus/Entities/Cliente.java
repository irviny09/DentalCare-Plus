package com.ubam.dentcare_plus.Entities;

import java.math.BigDecimal;
import java.sql.Date;

import com.ubam.dentcare_plus.User.User;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_ope_clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClienteId")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Cliente_UsuarioId")
    private User user;
    @Column(name = "Cliente_FechaNacimiento")
    private Date fechaNacimiento;
    @Column(name = "Cliente_SaldoPendiente")
    private BigDecimal saldoPendiente;
}
