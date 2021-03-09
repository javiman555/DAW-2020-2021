package com.trec.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trec.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

}