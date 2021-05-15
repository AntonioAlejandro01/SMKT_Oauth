/*
 * @Author AntonioAlejandro01
 * 
 * @link http://antonioalejandro.com
 * @link https://github.com/AntonioAlejandro01/SMKT_Users
 * 
 */
package com.antonioalejandro.smkt.oauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * The Class OauthProperties.
 */
@Getter
@Component
public class OauthProperties {

	/** The client id. */
	@Value("${config.security.oauth.client.id}")
	private String clientId;

	/** The client secret. */
	@Value("${config.security.oauth.client.secret}")
	private String clientSecret;

	/** The key JWT. */
	@Value("${config.security.oauth.jwt.key}")
	private String keyJWT;
}
