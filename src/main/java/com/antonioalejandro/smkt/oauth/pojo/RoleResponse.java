package com.antonioalejandro.smkt.oauth.pojo;

import java.util.List;

import lombok.Data;

@Data
public class RoleResponse {
	private String status;
	private String message;
	private Role role;
	private List<Role> roles;
	
	@Data
	public class Role  {
		private Long id;
		private String name;
	}
}
