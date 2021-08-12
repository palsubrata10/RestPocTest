package com.poc.RestPOC.controller;

import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.poc.RestPOC.exception.UserNotFoundException;
import com.poc.RestPOC.model.User;
import com.poc.RestPOC.services.UserService;


@RestController
public class UserContoller {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }
	
	 @GetMapping(path = "/jpa/users")
	 public List<User> retriveAllUsers(){
	    return userService.getAllUser();
	 }
	 
	  @GetMapping(path = "/jpa/users/{id}")
	    public ResponseEntity<User> retriveUserById(@PathVariable int id){
		  User user = userService.getUserById(id);
	        if(user == null) {
	        	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<User>(user,HttpStatus.OK);
	    }
	 
	 
	  @PostMapping(path = "/jpa/CreateUsers")
	    public ResponseEntity<Object> createUser(@RequestBody User user){
	        User saveUser = userService.createUser(user);
	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest().path("/{id}")
	                .buildAndExpand(saveUser.getId()).toUri();
	        return ResponseEntity.created(location).build();
	    }
}
