package com.antonioalejandro.smkt.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * SMKT Oauth Application Class
 * 
 * @author AntonioAlejandro01 - www.antonioalejandro.com
 * @version 1.0.0
 *
 */
@SpringBootApplication
@EnableAuthorizationServer
@EnableDiscoveryClient
public class SmktOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmktOauthApplication.class, args);
	}

}
