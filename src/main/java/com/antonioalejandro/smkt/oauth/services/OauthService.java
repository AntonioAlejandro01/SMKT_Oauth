/*
 * @Author AntonioAlejandro01
 * 
 * @link http://antonioalejandro.com
 * @link https://github.com/AntonioAlejandro01/SMKT_Users
 * 
 */
package com.antonioalejandro.smkt.oauth.services;

import com.antonioalejandro.smkt.oauth.model.RoleResponse;
import com.antonioalejandro.smkt.oauth.model.ScopeResponse;
import com.antonioalejandro.smkt.oauth.model.UserResponse;

/**
 * The Interface IOauthService.
 */
public interface OauthService {

	/**
	 * Find user by username.
	 *
	 * @param username the username
	 * @return the user response
	 */
	public UserResponse findUserByUsername(String username);

	/**
	 * Find role by name.
	 *
	 * @param roleName the role name
	 * @return the role response
	 */
	public RoleResponse findRoleByName(String roleName);

	/**
	 * Find scopes by role id.
	 *
	 * @param roleId the role id
	 * @return the scope response
	 */
	public ScopeResponse findScopesByRoleId(Long roleId);

}
