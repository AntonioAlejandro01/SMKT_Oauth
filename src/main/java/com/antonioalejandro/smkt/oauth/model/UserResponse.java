/*
 * @Author AntonioAlejandro01
 * 
 * @link http://antonioalejandro.com
 * @link https://github.com/AntonioAlejandro01/SMKT_Users
 * 
 */
package com.antonioalejandro.smkt.oauth.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class UserResponse.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * Instantiates a new user response.
 */
@Getter
@Setter
@ToString
public class UserResponse {

	/** The message. */
	private String message;

	/** The status. */
	private String status;

	/** The user. */
	private User user;

	/** The users. */
	private List<User> users;

	/**
	 * Instantiates a new user.
	 */
	@Getter
	@Setter
	@ToString
	public class User {

		/** The id. */
		private Long id;

		/** The name. */
		private String name;

		/** The lastname. */
		private String lastname;

		/** The username. */
		private String username;

		/** The password. */
		private String password;

		/** The email. */
		private String email;

		/** The role. */
		private String role;
	}
}
