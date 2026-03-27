package com.ubam.dentcare_plus.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ubam.dentcare_plus.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
