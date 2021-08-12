package com.poc.RestPOC.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.RestPOC.dao.UserRepository;
import com.poc.RestPOC.model.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	public User getUserById(Integer id) {
		return userRepository.findById(id).get();
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}

}
