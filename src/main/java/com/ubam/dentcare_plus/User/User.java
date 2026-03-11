package com.ubam.dentcare_plus.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_ope_usuarios")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioId")
    Integer id;

    @Column(name = "Usuario_Email")
    String username;

    @Column(name = "Usuario_Password")
    String password;

    @Column(name = "Usuario_Nombre")
    String name;

    @Column(name = "Usuario_Apellido")
    String apellido;

    @Column(name = "Usuario_Telefono")
    String telefono;

    @Column(name = "Usuario_Registro", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    LocalDateTime fechaRegistro;

    @Column(name = "Usuario_Activo")
    Boolean activo = true;
    
    @ManyToOne
    @JoinColumn(name = "Usuario_RolId")
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }
}
