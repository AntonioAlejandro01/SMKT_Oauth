package com.antonioalejandro.smkt.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.antonioalejandro.smkt.oauth.pojo.User;
import com.antonioalejandro.smkt.oauth.pojo.UserRegistrationDTO;


@FeignClient(name = "smkt-users")
public interface UserFeignClient {

	@GetMapping("/users/search/{username}")
	public User findByUserName(@PathVariable String username);

	@PutMapping("/users/user/{id}")
	public User update(@RequestBody User user, @PathVariable Long id);
	
	@PostMapping("/users/user")
	public User createUser(@RequestBody UserRegistrationDTO userRegistrationDTO);
	
	@DeleteMapping("/users/user/{id}")
	public String deleteUser(@PathVariable("id") final Long id);

}
