package com.antonioalejandro.smkt.oauth.clients.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.antonioalejandro.smkt.oauth.pojo.RoleResponse;
import com.antonioalejandro.smkt.oauth.pojo.ScopeResponse;
import com.antonioalejandro.smkt.oauth.pojo.UserResponse;
import com.antonioalejandro.smkt.oauth.services.IOauthService;
import com.antonioalejandro.smkt.oauth.utils.Constants;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private IOauthService oauthService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		final Map<String, Object> info = new HashMap<>();
		final UserResponse userResponse = oauthService.findUserByUsername(authentication.getName());
		final UserResponse.User user = userResponse.getUser();
		info.put(Constants.TOKEN_FIELD_NAME, user.getName());
		info.put(Constants.TOKEN_FIELD_LASTNAME, user.getLastname());
		info.put(Constants.TOKEN_FIELD_EMAIL, user.getEmail());
		info.put(Constants.TOKEN_FIELD_USERNAME, user.getUsername());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		((DefaultOAuth2AccessToken) accessToken).setScope(getScopesFromUser(user));
		return accessToken;
	}

	private Set<String> getScopesFromUser(UserResponse.User user) {
		RoleResponse roleResponse = oauthService.findRoleByName(user.getRole());
		if (roleResponse.getRole() == null) {
			return Set.of();
		}
		ScopeResponse scopeResponse = oauthService.findScopesByRoleId(roleResponse.getRole().getId());

		if (scopeResponse.getScopes() == null || scopeResponse.getScopes().isEmpty()) {
			return Set.of();
		}

		return Set.copyOf(scopeResponse.getScopes());

	}

}
