package com.antonioalejandro.smkt.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BootstrapConfig {
	@Bean
	public RestTemplate restTemplate() {

		final RestTemplate restTemplate = new RestTemplate();

		final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);
		requestFactory.setReadTimeout(10000);

		restTemplate.setRequestFactory(requestFactory);

		restTemplate.getInterceptors().add(new CustomClientHttpRequestInterceptor());

		return restTemplate;
	}
}