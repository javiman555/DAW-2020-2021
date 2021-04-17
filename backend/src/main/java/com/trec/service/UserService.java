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
	
	public User updateUser(User olduser,User changeduser) {
		
		User newuser = olduser;
		
		if(changeduser.getFirstName() != olduser.getFirstName() && changeduser.getFirstName() != null) {
			newuser.setFirstName(changeduser.getFirstName());
		}
		if(changeduser.getSurname() != olduser.getSurname() && changeduser.getSurname()!= null) {
			newuser.setSurname(changeduser.getSurname());
		}
		if(changeduser.getEmail() != olduser.getEmail() && changeduser.getEmail()!= null) {
			newuser.setEmail(changeduser.getEmail());
		}
		if(changeduser.getPhoneNumber() != olduser.getPhoneNumber() && changeduser.getPhoneNumber()!= 0) {
			newuser.setPhoneNumber(changeduser.getPhoneNumber());
		}
		
		return newuser;
	}
	
	public boolean existUser(User user,List<User> users) {
		boolean exist = false;
		for(User u : users) {
			if(user.getName().equals(u.getName())) {
				exist = true;
			}
		}
		return exist;
	}
	
	
	
	
}
