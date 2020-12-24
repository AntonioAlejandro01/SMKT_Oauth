package com.antonioalejandro.smkt.oauth.config;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;

@Getter
public class OauthProperties {
	@Value("${config.security.oauth.client.id}")
	private String clientId;
	@Value("${config.security.oauth.client.secret}")
	private String clientSecret;
	@Value("${config.security.oauth.jwt.key}")
	private String keyJWT;
}
