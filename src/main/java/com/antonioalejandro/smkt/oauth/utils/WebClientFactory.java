package com.antonioalejandro.smkt.oauth.utils;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientFactory {
	
	private WebClientFactory() {

	}

	public static WebClient getWebClient(String url, boolean isSearch) {
		return WebClient.builder().baseUrl(url)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, "X")
				.defaultHeader(Constants.HEADER_APP_KEY, isSearch ? Constants.HEADER_APP_KEY_VALUE: "").build();
	}

	public static String getURLInstanceService(String serviceId, DiscoveryClient client) {
		ServiceInstance instanceInfo = client.getInstances(serviceId).get(0);
		return String.format("http://%s:%s", instanceInfo.getHost(), instanceInfo.getPort());
	}

}
