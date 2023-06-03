package com.lifung.springbootlifung.service;

import java.util.List;

import com.lifung.springbootlifung.model.UserDto;

public interface UserService {

	/**
	 * Create a user
	 * 
	 * @param dto
	 * @throws Exception
	 */
	void createUser(final UserDto dto) throws Exception;

	/**
	 * Return information of a user
	 * 
	 * @param userName
	 * @return a user
	 * @throws Exception
	 */
	UserDto getUser(final String userName) throws Exception;

	/**
	 * Get all users 
	 * 
	 * @return all users
	 * @throws Exception
	 */
	List<UserDto> getAllUsers() throws Exception;


}