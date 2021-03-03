package com.anica.vjezba6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anica.vjezba6.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
