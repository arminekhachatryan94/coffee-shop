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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datastax.driver.core.utils.UUIDs;
import com.api.models.User;
import com.api.services.UserService;
import org.springframework.stereotype.Controller;
 
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("users")
public class UserController {
 
	@Autowired
	private UserService userService;
 
    @GetMapping("/")  
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable UUID id) {
		User user = userService.getUser(id);
		if(user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		boolean status = userService.createUser(
			user.getFirstName(),
			user.getLastName(),
			user.getEmail(),
			user.getPassword(),
			"customer"
		);
		return new ResponseEntity<String>(status + "", HttpStatus.CREATED);
		// if(status != null)
		// 	return new ResponseEntity<String>("Created", HttpStatus.CREATED);
		// return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/update/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody User user) {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();

		User oldUser = userService.getUser(id);

		if( oldUser != null ){
			if(firstName == null && lastName == null && email == null)
				return new ResponseEntity<String>("No values requested to be updated", HttpStatus.BAD_REQUEST);
			
			if(firstName == null) {
				firstName = oldUser.getFirstName();
			}
			if(lastName == null) {
				lastName = oldUser.getLastName();
			}
			if( email == null) {
				email = oldUser.getEmail();
			}

			boolean status = userService.updateUser(
				id, firstName, lastName, email
			);

			if(status)
				return new ResponseEntity<String>("Successfully updated user", HttpStatus.OK);
			return new ResponseEntity<String>("Error updating user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("User does not exist", HttpStatus.NOT_FOUND);
	}

	// @PutMapping("/update/role/{id}")
	// public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody String role) {

	// }

	// @PutMapping("/update/password/{id}")
	// public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody String password) {

	// }
}
