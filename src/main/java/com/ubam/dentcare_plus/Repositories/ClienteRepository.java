package com.ubam.dentcare_plus.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubam.dentcare_plus.Entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
}
