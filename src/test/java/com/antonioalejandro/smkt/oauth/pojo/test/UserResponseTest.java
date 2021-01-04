package com.antonioalejandro.smkt.oauth.pojo.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.antonioalejandro.smkt.oauth.UtilsForTesting;
import com.antonioalejandro.smkt.oauth.pojo.UserResponse;

class UserResponseTest {
	
	@Test
	void test() throws Exception {
		UserResponse response = new UserResponse();
		response.setMessage(UtilsForTesting.DATA_OK);
		response.setStatus(UtilsForTesting.DATA_OK);
		response.setUser(response.new User());
		response.setUsers(List.of());
		
		assertNotNull(response.getMessage());
		assertNotNull(response.getStatus());
		assertNotNull(response.getUser());
		assertNull(response.getUser().getId());
		assertNull(response.getUser().getEmail());
		assertNull(response.getUser().getLastname());
		assertNull(response.getUser().getName());
		assertNull(response.getUser().getPassword());
		assertNull(response.getUser().getRole());
		assertNull(response.getUser().getUsername());
	
		assertNotNull(response.getUsers());
		
		assertNotNull(response.toString());
		assertNotNull(response.getUser().toString());
	}
}
