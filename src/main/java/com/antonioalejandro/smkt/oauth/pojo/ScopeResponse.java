/*
 * @Author AntonioAlejandro01
 * 
 * @link http://antonioalejandro.com
 * @link https://github.com/AntonioAlejandro01/SMKT_Users
 * 
 */
package com.antonioalejandro.smkt.oauth.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Instantiates a new scope response.
 */
@Getter
@Setter
@ToString
public class ScopeResponse {

	/** The message. */
	private String message;

	/** The status. */
	private String status;

	/** The scopes. */
	private List<String> scopes;

}
