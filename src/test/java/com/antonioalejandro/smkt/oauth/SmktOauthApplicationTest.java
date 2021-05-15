package com.antonioalejandro.smkt.oauth;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmktOauthApplicationTest {

	@Test
	void test() throws Exception {
		assertDoesNotThrow(() -> {
			SmktOauthApplication.main(new String[] {});
		});
	}
}
