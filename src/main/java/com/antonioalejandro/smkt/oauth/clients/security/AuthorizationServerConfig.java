package com.antonioalejandro.smkt.oauth.clients.security;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

import com.antonioalejandro.smkt.oauth.pojo.Oauth2Properties;



@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final int RESTTEMPLATE_TIMEOUT = 300000;

	private static final String WRITE = "write";

	private static final String READ = "read";

	private static final String REFRESH_TOKEN = "refresh_token";

	private static final String PASS = "password";

	private static final int REFRESH_TOKEN_VALID_TIME = 7200;

	private static final int ACCESS_TOKEN_VALID_TIME = 3600;

	@Autowired
	private Oauth2Properties oauth2Properties;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private InfoAdicionalToken infoAdicionalToken;

	private static final Logger log = Logger.getLogger(AuthorizationServerConfig.class.getName());

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		log.log(Level.INFO, " Property 1: {0} ", oauth2Properties.getOauthClientId());
		log.log(Level.INFO, " Property 2: {0} ", oauth2Properties.getOauthClientSecret());
		log.log(Level.INFO, " Property 3: {0} ", oauth2Properties.getOauthLoginSuccess());
		log.log(Level.INFO, " Property 4: {0} ", oauth2Properties.getOauthLoginFail());
		log.log(Level.INFO, " Property 5: {0} ", oauth2Properties.getOauthJWTKey());
		

		clients.inMemory()
				.withClient(oauth2Properties.getOauthClientId())
				.secret(passwordEncoder.encode(oauth2Properties.getOauthClientSecret())).scopes(READ, WRITE)
				.authorizedGrantTypes(getSecretGrant(), REFRESH_TOKEN)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALID_TIME)
				.refreshTokenValiditySeconds(REFRESH_TOKEN_VALID_TIME);
	}

	public String getSecretGrant() {
		return PASS;
	}
	

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter()).tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(oauth2Properties.getOauthJWTKey());
		return tokenConverter;
	}

}
