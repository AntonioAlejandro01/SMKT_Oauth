package com.antonioalejandro.smkt.oauth.clients.security.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.antonioalejandro.smkt.oauth.config.security.InfoAdicionalToken;
import com.antonioalejandro.smkt.oauth.model.RoleResponse;
import com.antonioalejandro.smkt.oauth.model.RoleResponse.Role;
import com.antonioalejandro.smkt.oauth.model.ScopeResponse;
import com.antonioalejandro.smkt.oauth.model.UserResponse;
import com.antonioalejandro.smkt.oauth.model.UserResponse.User;
import com.antonioalejandro.smkt.oauth.services.impl.OauthServiceImpl;

class InfoAdicionalTokenTest {

	@InjectMocks
	private InfoAdicionalToken infoAdicionalToken;
	@Mock
	private OauthServiceImpl service;

	@Mock
	private OAuth2Authentication authentication;

	@Mock
	private DefaultOAuth2AccessToken accessToken;

	private static final String NAME = "name";

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test() throws Exception {
		UserResponse response = new UserResponse();
		User user = response.new User();
		user.setEmail("ok");
		user.setLastname("ok");
		user.setName("ok");
		user.setPassword("ok");
		user.setRole("ok");
		user.setUsername("ok");
		response.setUser(user);
		when(authentication.getName()).thenReturn(NAME);
		when(service.findUserByUsername(authentication.getName())).thenReturn(response);
		RoleResponse roleResponse = new RoleResponse();
		Role role = roleResponse.new Role();
		role.setId(0L);
		role.setName("ok");
		roleResponse.setRole(role);
		when(service.findRoleByName(role.getName())).thenReturn(roleResponse);
		ScopeResponse scopeResponse = new ScopeResponse();
		List<String> scopes = new ArrayList<String>(1);
		scopes.add("ok");
		scopeResponse.setScopes(scopes);
		when(service.findScopesByRoleId(0L)).thenReturn(scopeResponse);

		OAuth2AccessToken token = infoAdicionalToken.enhance(accessToken, authentication);

		assertNotNull(token);

	}

	@Test
	void testEmpty() throws Exception {
		UserResponse response = new UserResponse();
		User user = response.new User();
		user.setEmail("ok");
		user.setLastname("ok");
		user.setName("ok");
		user.setPassword("ok");
		user.setRole("ok");
		user.setUsername("ok");
		response.setUser(user);
		when(authentication.getName()).thenReturn(NAME);
		when(service.findUserByUsername(authentication.getName())).thenReturn(response);
		RoleResponse roleResponse = new RoleResponse();
		roleResponse.setRole(null);
		when(service.findRoleByName("ok")).thenReturn(roleResponse);

		OAuth2AccessToken token = infoAdicionalToken.enhance(accessToken, authentication);

		assertNotNull(token);

	}

	@Test
	void testEmptyScopes() throws Exception {
		UserResponse response = new UserResponse();
		User user = response.new User();
		user.setEmail("ok");
		user.setLastname("ok");
		user.setName("ok");
		user.setPassword("ok");
		user.setRole("ok");
		user.setUsername("ok");
		response.setUser(user);
		when(authentication.getName()).thenReturn(NAME);
		when(service.findUserByUsername(authentication.getName())).thenReturn(response);
		RoleResponse roleResponse = new RoleResponse();
		Role role = roleResponse.new Role();
		role.setId(0L);
		role.setName("ok");
		roleResponse.setRole(role);
		when(service.findRoleByName(role.getName())).thenReturn(roleResponse);
		ScopeResponse scopeResponse = new ScopeResponse();

		scopeResponse.setScopes(List.of());
		when(service.findScopesByRoleId(0L)).thenReturn(scopeResponse);

		OAuth2AccessToken token = infoAdicionalToken.enhance(accessToken, authentication);

		assertNotNull(token);

	}
}
