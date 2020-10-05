package com.antonioalejandro.smkt.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.antonioalejandro.smkt.oauth.pojo.Oauth2Properties;



@Configuration
public class PersistentContext {

	@Bean()
	public Oauth2Properties getOauth2Properties(final Environment env) {

		final Oauth2Properties oauth2PropertiesProperties = new Oauth2Properties();

		oauth2PropertiesProperties.setOauthClientId(env.getProperty("config.security.oauth.client.id"));
		oauth2PropertiesProperties.setOauthClientSecret(env.getProperty("config.security.oauth.client.secret"));
		oauth2PropertiesProperties.setOauthClient(env.getProperty("config.security.oauth.client.client"));
		oauth2PropertiesProperties.setOauthLoginSuccess(env.getProperty("oauth2.url.loginsuccess"));
		oauth2PropertiesProperties.setOauthLoginFail(env.getProperty("oauth2.url.loginfail"));

		oauth2PropertiesProperties.setOauthJWTKey(env.getProperty("config.security.oauth.jwt.key"));

		return oauth2PropertiesProperties;
	}

}
