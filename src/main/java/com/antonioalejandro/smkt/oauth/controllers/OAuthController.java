package com.antonioalejandro.smkt.oauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonioalejandro.smkt.oauth.pojo.User;
import com.antonioalejandro.smkt.oauth.pojo.UserRegistrationDTO;
import com.antonioalejandro.smkt.oauth.services.AuthService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth/")
public class OAuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody final UserRegistrationDTO userRegistrationDTO){
		final User user = authService.create(userRegistrationDTO);
		return user == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST):new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody final User user, @PathVariable("id") final Long id){
		return new ResponseEntity<User>(authService.update(user, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") final Long id){
		return new ResponseEntity<String>(authService.delete(id), HttpStatus.OK);
	}
	
	
	
	
}
