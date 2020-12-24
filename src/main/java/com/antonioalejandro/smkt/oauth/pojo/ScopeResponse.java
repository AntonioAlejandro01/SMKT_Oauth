package com.antonioalejandro.smkt.oauth.pojo;

import java.util.List;

import lombok.Data;

@Data
public class ScopeResponse {
	
	private String message;
	private String status;
	private List<String> scopes;
	
}
