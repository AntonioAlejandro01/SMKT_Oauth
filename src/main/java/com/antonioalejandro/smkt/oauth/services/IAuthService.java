package com.antonioalejandro.smkt.oauth.services;

import com.antonioalejandro.smkt.oauth.pojo.User;
import com.antonioalejandro.smkt.oauth.pojo.UserRegistrationDTO;

public interface IAuthService {

	/**
	 * 
	 * @param userRegistrationDTO
	 * @return
	 */
	public User create(UserRegistrationDTO userRegistrationDTO);

	/**
	 * 
	 * @param user
	 * @param id
	 * @return
	 */
	public User update(User user, String oldUsername);

	/**
	 * 
	 * @param user
	 * @return
	 */
	public String delete(Long id);

}
