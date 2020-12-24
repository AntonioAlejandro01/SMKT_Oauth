package com.antonioalejandro.smkt.oauth.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.antonioalejandro.smkt.oauth.pojo.RoleResponse;
import com.antonioalejandro.smkt.oauth.pojo.ScopeResponse;
import com.antonioalejandro.smkt.oauth.pojo.UserResponse;
import com.antonioalejandro.smkt.oauth.utils.WebClientFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OauthService implements IOauthService, UserDetailsService {

	@Value("${usersId}")
	private String usersId;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			final UserResponse userResponse = findUserByUsername(username);
			if (userResponse.getUser() == null) {
				throw new NullPointerException(userResponse.getMessage());
			}
			final UserResponse.User user = userResponse.getUser();
			final GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
			log.info("Usuario autenticado: " + username);
			log.info("ID: " + user.getId() + ", USERNAME: " + user.getUsername() + ", EMAIL: " + user.getEmail()
					+ "Role: " + authority.getAuthority());

			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
					true, true, true, Arrays.asList(authority));

		} catch (final Exception e) {
			log.error(e.getMessage());
			log.error("Error en el login, no existe el usuario '" + username + "' en el sistema");
			throw new UsernameNotFoundException(
					"Error en el login, no existe el usuario '" + username + "' en el sistema");
		}

	}

	@Override
	public UserResponse findUserByUsername(String username) {
		WebClient client = WebClientFactory.getWebClient(WebClientFactory.getURLInstanceService(usersId, discoveryClient),true);
		HttpEntity<UserResponse> entity = client.get().uri(String.format("/users/search?filter=username&value=%s", username)).retrieve().toEntity(UserResponse.class).block();
		return entity.getBody();
	}

	@Override
	public RoleResponse findRoleByName(String roleName) {
		WebClient client = WebClientFactory.getWebClient(WebClientFactory.getURLInstanceService(usersId, discoveryClient),false);
		return client.get().uri(String.format("/roles/%s", roleName)).retrieve().bodyToMono(RoleResponse.class).block();
	}

	@Override
	public ScopeResponse findScopesByRoleId(Long roleId) {
		WebClient client = WebClientFactory.getWebClient(WebClientFactory.getURLInstanceService(usersId, discoveryClient),false);
		return client.get().uri(String.format("/scopes?roleId=%d", roleId)).retrieve().bodyToMono(ScopeResponse.class)
				.block();
	}

}
