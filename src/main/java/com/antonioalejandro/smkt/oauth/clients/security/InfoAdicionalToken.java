package com.antonioalejandro.smkt.oauth.clients.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.antonioalejandro.smkt.oauth.pojo.User;
import com.antonioalejandro.smkt.oauth.services.IUserService;



@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private IUserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		final Map<String, Object> info = new HashMap<String, Object>();

		final User user = userService.findByUserName(authentication.getName());
		info.put("name", user.getName());
		info.put("lastname", user.getLastname());
		info.put("email", user.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
