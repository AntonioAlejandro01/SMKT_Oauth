package com.antonioalejandro.smkt.oauth.config.security;

import java.util.HashMap;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.antonioalejandro.smkt.oauth.model.UserResponse;
import com.antonioalejandro.smkt.oauth.services.OauthService;
import com.antonioalejandro.smkt.oauth.utils.Constants;

/**
 * Info Adicional Token
 * @author AntonioAlejandro01 - www.antonioalejandro.com
 * @version 1.0.0
 *
 */
@Component
public class InfoAdicionalToken implements TokenEnhancer {

	/** The oauth service. */
	@Autowired
	private OauthService oauthService;

	/**
	 * Enhance.
	 *
	 * @param accessToken    the access token
	 * @param authentication the authentication
	 * @return the o auth 2 access token
	 */
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		final var info = new HashMap<String, Object>();
		final var userResponse = oauthService.findUserByUsername(authentication.getName());
		final var user = userResponse.getUser();
		info.put(Constants.TOKEN_FIELD_NAME, user.getName());
		info.put(Constants.TOKEN_FIELD_LASTNAME, user.getLastname());
		info.put(Constants.TOKEN_FIELD_EMAIL, user.getEmail());
		info.put(Constants.TOKEN_FIELD_USERNAME, user.getUsername());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		((DefaultOAuth2AccessToken) accessToken).setScope(getScopesFromUser(user));
		return accessToken;
	}

	/**
	 * Gets the scopes from user.
	 *
	 * @param user the user
	 * @return the scopes from user
	 */
	private Set<String> getScopesFromUser(UserResponse.User user) {
		var roleResponse = oauthService.findRoleByName(user.getRole());
		if (roleResponse.getRole() == null) {
			return Set.of();
		}
		var scopeResponse = oauthService.findScopesByRoleId(roleResponse.getRole().getId());

		if (scopeResponse.getScopes() == null || scopeResponse.getScopes().isEmpty()) {
			return Set.of();
		}

		return Set.copyOf(scopeResponse.getScopes());

	}

}
