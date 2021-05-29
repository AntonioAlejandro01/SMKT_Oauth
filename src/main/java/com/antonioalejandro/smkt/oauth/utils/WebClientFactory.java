package com.antonioalejandro.smkt.oauth.utils;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Web Client Factory Class
 * 
 * @author AntonioAlejandro01 - www.antonioalejandro.com
 * @version 1.0.0
 *
 */
public class WebClientFactory {

	/**
	 * Instantiates a new web client factory.
	 */
	private WebClientFactory() {

	}

	/**
	 * Gets the web client.
	 *
	 * @param url      the url
	 * @param isSearch the is search
	 * @return the web client
	 */
	public static WebClient getWebClient(String url, boolean isSearch) {
		return WebClient.builder().baseUrl(url)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, "X")
				.defaultHeader(Constants.HEADER_APP_KEY, isSearch ? Constants.HEADER_APP_KEY_VALUE : "").build();
	}

	/**
	 * Gets the URL instance service.
	 *
	 * @param serviceId the service id
	 * @param client    the client
	 * @return the URL instance service
	 */
	public static String getURLInstanceService(String serviceId, DiscoveryClient client) {
		ServiceInstance instanceInfo = client.getInstances(serviceId).get(0);
		return String.format("http://%s:%s", instanceInfo.getHost(), instanceInfo.getPort());
	}

}
