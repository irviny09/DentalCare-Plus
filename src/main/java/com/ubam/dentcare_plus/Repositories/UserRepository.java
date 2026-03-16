package com.ubam.dentcare_plus.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubam.dentcare_plus.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
}
