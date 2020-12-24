package com.antonioalejandro.smkt.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistentContext {

	@Bean
	public OauthProperties oauthProperties() {
		return new OauthProperties();
	}

}
