package com.antonioalejandro.smkt.oauth.pojo.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.antonioalejandro.smkt.oauth.UtilsForTesting;
import com.antonioalejandro.smkt.oauth.pojo.ScopeResponse;

class ScopeResponseTest {
	@Test
	void test() throws Exception {
		ScopeResponse response = new ScopeResponse();
		response.setMessage(UtilsForTesting.DATA_OK);
		response.setStatus(UtilsForTesting.DATA_OK);
		response.setScopes(List.of());
		
		assertNotNull(response.getMessage());
		assertNotNull(response.getStatus());
		assertNotNull(response.getScopes());
		assertNotNull(response.toString());
	}
}
