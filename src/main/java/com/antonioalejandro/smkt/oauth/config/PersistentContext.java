/*
 * @Author AntonioAlejandro01
 * 
 * @link http://antonioalejandro.com
 * @link https://github.com/AntonioAlejandro01/SMKT_Users
 * 
 */
package com.antonioalejandro.smkt.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class PersistentContext.
 */
@Configuration
public class PersistentContext {

	/**
	 * Oauth properties.
	 *
	 * @return the oauth properties
	 */
	@Bean
	public OauthProperties oauthProperties() {
		return new OauthProperties();
	}

}
