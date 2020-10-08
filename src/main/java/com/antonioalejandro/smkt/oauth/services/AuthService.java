package com.antonioalejandro.smkt.oauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonioalejandro.smkt.oauth.clients.UserFeignClient;
import com.antonioalejandro.smkt.oauth.pojo.User;
import com.antonioalejandro.smkt.oauth.pojo.UserRegistrationDTO;

@Service
public class AuthService implements IAuthService{
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Override
	public User create(UserRegistrationDTO userRegistrationDTO) {
		return userFeignClient.createUser(userRegistrationDTO);
	}

	@Override
	public User update(User user, String oldUsername) {
		return userFeignClient.update(user, userFeignClient.findByUserName(oldUsername).getId());
	}

	@Override
	public String delete(Long id) {
		return userFeignClient.deleteUser(id);
	}
	
	
	
	
}
