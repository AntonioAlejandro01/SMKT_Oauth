package com.antonioalejandro.smkt.oauth.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Scope Response Class
 * 
 * @author AntonioAlejandro01 - www.antonioalejandro.com
 * @version 1.0.0
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
