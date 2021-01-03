package com.antonioalejandro.smkt.oauth.pojo.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.antonioalejandro.smkt.oauth.UtilsForTesting;
import com.antonioalejandro.smkt.oauth.pojo.RoleResponse;

class RoleResponseTest {

	@Test
	void test() throws Exception {
		RoleResponse response = new RoleResponse();
		response.setMessage(UtilsForTesting.DATA_OK);
		response.setStatus(UtilsForTesting.DATA_OK);
		response.setRole(response.new Role());
		response.setRoles(List.of());
		assertNotNull(response.getMessage());
		assertNotNull(response.getRole());
		assertNotNull(response.getRoles());
		assertNotNull(response.toString());
		assertNotNull(response.getRole().toString());
		
	}
}
