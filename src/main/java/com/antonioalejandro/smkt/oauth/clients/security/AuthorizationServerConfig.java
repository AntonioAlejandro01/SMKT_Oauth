/*
 * @Author AntonioAlejandro01
 * 
 * @link http://antonioalejandro.com
 * @link https://github.com/AntonioAlejandro01/SMKT_Users
 * 
 */
package com.antonioalejandro.smkt.oauth.clients.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.antonioalejandro.smkt.oauth.config.OauthProperties;

import lombok.extern.slf4j.Slf4j;




/**
 * The Class AuthorizationServerConfig.
 */
@Slf4j
@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	/** The Constant REFRESH_TOKEN. */
	private static final String REFRESH_TOKEN = "refresh_token";

	/** The Constant PASS. */
	private static final String PASS = "password";

	/** The Constant REFRESH_TOKEN_VALID_TIME. */
	private static final int REFRESH_TOKEN_VALID_TIME = 7200;

	/** The Constant ACCESS_TOKEN_VALID_TIME. */
	private static final int ACCESS_TOKEN_VALID_TIME = 3600;

	/** The oauth 2 properties. */
	@Autowired
	private OauthProperties oauth2Properties;

	/** The password encoder. */
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;

	/** The info adicional token. */
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;

	/**
	 * Configure.
	 *
	 * @param security the security
	 * @throws Exception the exception
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("isAuthenticated()").checkTokenAccess("isAuthenticated()");
	}

	/**
	 * Configure.
	 *
	 * @param clients the clients
	 * @throws Exception the exception
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		log.info("Property 1: {}",oauth2Properties.getClientId());
		log.info("Property 2: {}", oauth2Properties.getClientSecret());
		log.info("Property 3: {}", oauth2Properties.getKeyJWT());

		clients.inMemory().withClient(oauth2Properties.getClientId())
				.secret(passwordEncoder.encode(oauth2Properties.getClientSecret()))
				.authorizedGrantTypes(getSecretGrant(), REFRESH_TOKEN).scopes("READ")
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALID_TIME)
				.refreshTokenValiditySeconds(REFRESH_TOKEN_VALID_TIME);
	}

	/**
	 * Gets the secret grant.
	 *
	 * @return the secret grant
	 */
	public String getSecretGrant() {
		return PASS;
	}

	/**
	 * Configure.
	 *
	 * @param endpoints the endpoints
	 * @throws Exception the exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter()).tokenEnhancer(tokenEnhancerChain);
	}

	/**
	 * Token store.
	 *
	 * @return the jwt token store
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * Access token converter.
	 *
	 * @return the jwt access token converter
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(oauth2Properties.getKeyJWT());
		return tokenConverter;
	}

}
