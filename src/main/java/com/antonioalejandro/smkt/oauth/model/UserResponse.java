package com.antonioalejandro.smkt.oauth.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User Response Class
 * 
 * @author AntonioAlejandro01 - www.antonioalejandro.com
 * @version 1.0.0
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
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
	 * User Class
	 * 
	 * @author AntonioAlejandro01 - www.antonioalejandro.com
	 * @version 1.0.0
	 *
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
