package com.antonioalejandro.smkt.oauth.pojo;

public class Oauth2Properties {

	private String oauthClientId;
	private String oauthClientSecret;
	private String oauthLoginSuccess;
	private String oauthLoginFail;
	private String oauthClient;
	private String oauthJWTKey;

	public String getOauthClientId() {
		return oauthClientId;
	}

	public void setOauthClientId(String oauthClientId) {
		this.oauthClientId = oauthClientId;
	}

	public String getOauthClientSecret() {
		return oauthClientSecret;
	}

	public void setOauthClientSecret(String oauthClientSecret) {
		this.oauthClientSecret = oauthClientSecret;
	}

	public String getOauthLoginSuccess() {
		return oauthLoginSuccess;
	}

	public void setOauthLoginSuccess(String oauthLoginSuccess) {
		this.oauthLoginSuccess = oauthLoginSuccess;
	}

	public String getOauthLoginFail() {
		return oauthLoginFail;
	}

	public void setOauthLoginFail(String oauthLoginFail) {
		this.oauthLoginFail = oauthLoginFail;
	}

	public String getOauthJWTKey() {
		return oauthJWTKey;
	}

	public void setOauthJWTKey(String oauthJWTKey) {
		this.oauthJWTKey = oauthJWTKey;
	}

	public String getOauthClient() {
		return oauthClient;
	}

	public void setOauthClient(String oauthClient) {
		this.oauthClient = oauthClient;
	}


}
