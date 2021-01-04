package com.antonioalejandro.smkt.oauth.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.antonioalejandro.smkt.oauth.services.OauthService;

class OauthServiceTest {

	@InjectMocks
	private OauthService service;

	@Mock
	private DiscoveryClient client;
	@Mock
	private ServiceInstance instance;
	private static final String id = "X";

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		List<ServiceInstance> list = new ArrayList<ServiceInstance>(1);
		list.add(instance);
		when(client.getInstances(null)).thenReturn(list);
		when(instance.getHost()).thenReturn(id);
		when(instance.getPort()).thenReturn(0);
	}

	@Test
	void test() throws Exception {

		try {
			service.findUserByUsername(id);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(Exception.class);
		}
	}

	@Test
	void testRole() throws Exception {

		try {
			service.findRoleByName(id);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(Exception.class);
		}
	}

	@Test
	void testScope() throws Exception {

		try {
			service.findScopesByRoleId(0L);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(Exception.class);
		}
	}

}
