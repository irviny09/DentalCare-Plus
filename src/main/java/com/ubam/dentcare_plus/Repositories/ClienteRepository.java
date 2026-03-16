package com.ubam.dentcare_plus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ubam.dentcare_plus.entities.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
    @Query(value = "SELECT c.* FROM tbl_ope_clientes c " +
            "INNER JOIN tbl_ope_usuarios u ON c.Cliente_UsuarioId = u.UsuarioId " +
            "WHERE u.Usuario_Email = :email", nativeQuery = true)
    Optional<Cliente> findByEmail(@Param("email") String email);
}
