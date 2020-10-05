package com.antonioalejandro.smkt.oauth.services;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.antonioalejandro.smkt.oauth.clients.UserFeignClient;
import com.antonioalejandro.smkt.oauth.pojo.User;

import feign.FeignException;

@Service
public class UserService implements IUserService, UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			final User user = client.findByUserName(username);
			final GrantedAuthority authority = new SimpleGrantedAuthority(user.getName());
			log.info("Usuario autenticado: " + username);
			log.info("ID: " + user.getId()+", USERNAME: "+ user.getUsername()+", EMAIL: "+ user.getEmail());

			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					true, true, true, true, Arrays.asList(authority));

		} catch (final FeignException e) {
			log.error(e.getMessage());
			log.error("Error en el login, no existe el usuario '" + username + "' en el sistema");
			throw new UsernameNotFoundException(
					"Error en el login, no existe el usuario '" + username + "' en el sistema");
		}

	}

	@Override
	public User findByUserName(String username) {
		return client.findByUserName(username);
	}

	@Override
	public User update(User usuario, Long id) {
		return client.update(usuario, id);
	}

}
