package com.antonioalejandro.smkt.oauth.services.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.antonioalejandro.smkt.oauth.model.RoleResponse;
import com.antonioalejandro.smkt.oauth.model.ScopeResponse;
import com.antonioalejandro.smkt.oauth.model.UserResponse;
import com.antonioalejandro.smkt.oauth.services.OauthService;
import com.antonioalejandro.smkt.oauth.utils.WebClientFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * Oauth Service Implementation Class
 * 
 * @author AntonioAlejandro01 - www.antonioalejandro.com
 * @version 1.0.0
 *
 */
@Slf4j
@Service
public class OauthServiceImpl implements OauthService, UserDetailsService {

	/** The users id. */
	@Value("${usersId}")
	private String usersId;

	/** The discovery client. */
	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final var userResponse = findUserByUsername(username);
		if (userResponse.getUser() == null) {
			throw new UsernameNotFoundException(String.format("User %s don't exists", username));
		}
		final var user = userResponse.getUser();
		final GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		log.info("Usuario {} autenticado", user.getUsername());
		log.info("User: {}", user);

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
				true, true, true, Arrays.asList(authority));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserResponse findUserByUsername(String username) {
		var client = WebClientFactory.getWebClient(WebClientFactory.getURLInstanceService(usersId, discoveryClient),
				true);
		return client.get().uri(String.format("/users/search?filter=username&value=%s", username)).retrieve()
				.bodyToMono(UserResponse.class).block();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RoleResponse findRoleByName(String roleName) {
		var client = WebClientFactory.getWebClient(WebClientFactory.getURLInstanceService(usersId, discoveryClient),
				false);
		return client.get().uri(String.format("/roles/%s", roleName)).retrieve().bodyToMono(RoleResponse.class).block();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScopeResponse findScopesByRoleId(Long roleId) {
		var client = WebClientFactory.getWebClient(WebClientFactory.getURLInstanceService(usersId, discoveryClient),
				false);
		return client.get().uri(String.format("/scopes?roleId=%d", roleId)).retrieve().bodyToMono(ScopeResponse.class)
				.block();
	}

}
