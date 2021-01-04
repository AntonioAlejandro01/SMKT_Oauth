package com.antonioalejandro.smkt.oauth.utils.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.antonioalejandro.smkt.oauth.utils.WebClientFactory;

class WebFactoryTest {
	@Mock
	private DiscoveryClient client;
	@Mock
	private ServiceInstance instance;

	private static final String id = "X";

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test() throws Exception {
		assertNotNull(WebClientFactory.getWebClient(id, true));
		assertNotNull(WebClientFactory.getWebClient(id, false));
	}

	@Test
	void testURL() throws Exception {
		List<ServiceInstance> list = new ArrayList<ServiceInstance>(1);
		list.add(instance);
		when(client.getInstances(id)).thenReturn(list);
		when(instance.getHost()).thenReturn(id);
		when(instance.getPort()).thenReturn(0);
		String url = WebClientFactory.getURLInstanceService(id, client);

		assertNotNull(url);
		assertEquals(String.format("http://%s:%s", id, 0), url);
	}
}
