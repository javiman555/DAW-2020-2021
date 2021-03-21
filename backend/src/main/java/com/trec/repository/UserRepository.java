package com.trec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trec.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

}