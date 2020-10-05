package com.antonioalejandro.smkt.oauth.services;

import com.antonioalejandro.smkt.oauth.pojo.User;

public interface IUserService {

	public User findByUserName(String username);

	public User update(User usuario, Long id);
}
