package com.ubam.dentcare_plus.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubam.dentcare_plus.Entities.Dentista;

public interface DentistaRepository extends JpaRepository<Dentista, Integer>{
    
}
