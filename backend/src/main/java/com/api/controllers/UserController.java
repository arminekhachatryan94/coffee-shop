package com.api.controllers;
 
import java.util.List;
import java.util.Optional;
import java.util.UUID;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datastax.driver.core.utils.UUIDs;
import com.api.models.User;
import com.api.services.UserService;
import org.springframework.stereotype.Controller;
 
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("users")
public class UserController {
 
	@Autowired
	private UserService UserService;
 
    @GetMapping("/")  
	public ResponseEntity<?> getAllUsers() {
		List<User> users = UserService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable UUID id) {
		User user = UserService.getUser(id);
		if( user != null )
			return new ResponseEntity<User>(user, HttpStatus.OK);
		else
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
}
