package com.antonioalejandro.smkt.oauth.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Role Response Class
 * @author AntonioAlejandro01 - www.antonioalejandro.com
 * @version 1.0.0
 *
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
	 * Role Class
	 * @author AntonioAlejandro01 - www.antonioalejandro.com
	 *	@version 1.0.0
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
