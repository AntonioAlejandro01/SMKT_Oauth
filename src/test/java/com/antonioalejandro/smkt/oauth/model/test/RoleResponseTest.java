package com.antonioalejandro.smkt.oauth.model.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.antonioalejandro.smkt.oauth.model.RoleResponse;

class RoleResponseTest {

	@Test
	void test() throws Exception {
		RoleResponse response = new RoleResponse();
		response.setMessage("ok");
		response.setStatus("ok");
		response.setRole(response.new Role());
		response.setRoles(List.of());
		assertNotNull(response.getMessage());
		assertNotNull(response.getRole());
		assertNotNull(response.getRoles());
		assertNotNull(response.toString());
		assertNotNull(response.getRole().toString());

	}
}
