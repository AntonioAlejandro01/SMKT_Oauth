package com.antonioalejandro.smkt.oauth.model.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.antonioalejandro.smkt.oauth.model.ScopeResponse;

class ScopeResponseTest {
	@Test
	void test() throws Exception {
		ScopeResponse response = new ScopeResponse();
		response.setMessage("ok");
		response.setStatus("ok");
		response.setScopes(List.of());

		assertNotNull(response.getMessage());
		assertNotNull(response.getStatus());
		assertNotNull(response.getScopes());
		assertNotNull(response.toString());
	}
}
