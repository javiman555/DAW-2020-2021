package com.trec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trec.model.User;
import com.trec.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}
	
	public boolean exist(long id) {
		return userRepository.existsById(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void deleteById(long id) {
		userRepository.deleteById(id);
	}
	public Optional<User> findByName(String name) {
		return userRepository.findByName(name);
	}
	
	//Falta método para comprobar si es administrador o no el usuario
	
}
