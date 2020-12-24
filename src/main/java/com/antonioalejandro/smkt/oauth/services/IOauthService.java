package com.antonioalejandro.smkt.oauth.services;

import com.antonioalejandro.smkt.oauth.pojo.RoleResponse;
import com.antonioalejandro.smkt.oauth.pojo.ScopeResponse;
import com.antonioalejandro.smkt.oauth.pojo.UserResponse;

public interface IOauthService {

	public UserResponse findUserByUsername(String username);
	
	public RoleResponse findRoleByName(String roleName);
	
	public ScopeResponse findScopesByRoleId(Long roleId);
	
}
