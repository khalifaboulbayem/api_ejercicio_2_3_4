package com.api.ejercicio234.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ejercicio234.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

	Optional<User> findByUsername(String username);
}