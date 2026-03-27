package com.ubam.dentcare_plus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubam.dentcare_plus.entities.Estudios;

@Repository
public interface EstudiosRepository extends JpaRepository<Estudios, Integer> {
}
