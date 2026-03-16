package com.ubam.dentcare_plus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ubam.dentcare_plus.entities.Dentista;

public interface DentistaRepository extends JpaRepository<Dentista, Integer> {

    @Query(value = "SELECT d.* FROM tbl_ope_dentistas d " +
            "INNER JOIN tbl_ope_usuarios u ON d.Dentista_UsuarioId = u.UsuarioId " +
            "WHERE u.Usuario_Email = :email", nativeQuery = true)
    Optional<Dentista> findByEmail(@Param("email") String email);
}
