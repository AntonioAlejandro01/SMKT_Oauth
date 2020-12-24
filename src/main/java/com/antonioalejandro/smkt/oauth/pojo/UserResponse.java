package com.antonioalejandro.smkt.oauth.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserResponse {
	
	private String message;
	
	private String status;

	private User user;

	private List<User> users;

	@Data
	public class User {
		private Long id;
		private String name;
		private String lastname;
		private String username;
		private String password;
		private String email;
		private String role;
	}
}
