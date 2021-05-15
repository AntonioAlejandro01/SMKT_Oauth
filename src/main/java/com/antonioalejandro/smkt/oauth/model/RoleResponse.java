/*
 * @Author AntonioAlejandro01
 * 
 * @link http://antonioalejandro.com
 * @link https://github.com/AntonioAlejandro01/SMKT_Users
 * 
 */
package com.antonioalejandro.smkt.oauth.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new role response.
 */
@Getter
@Setter
@ToString
public class RoleResponse {

	/** The status. */
	private String status;

	/** The message. */
	private String message;

	/** The role. */
	private Role role;

	/** The roles. */
	private List<Role> roles;

	/**
	 * Instantiates a new role.
	 */
	@Getter
	@Setter
	@ToString
	public class Role {

		/** The id. */
		private Long id;

		/** The name. */
		private String name;
	}
}
