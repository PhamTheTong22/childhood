package com.lifung.springbootlifung.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lifung.springbootlifung.entity.Users;
import com.lifung.springbootlifung.model.UserDto;
import com.lifung.springbootlifung.repositories.UserDao;


/*
 * This class is UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDao = userDao;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void createUser(UserDto dto) throws Exception {
		Users user = new Users();
		user.setUserName(dto.getUserName());
		user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		user.setRole(dto.getRole());
		userDao.save(user);
	}

	@Override
	public UserDto getUser(final String userName) throws Exception {
		final Users user = userDao.findByUserName(userName);
		if (user == null) {
			return null;
		}
		UserDto dto = new UserDto();
		dto.setUserName(user.getUserName());
		dto.setRole(user.getRole());
		return dto;
	}

	@Override
	public List<UserDto> getAllUsers() throws Exception {
		List<UserDto> ls = new ArrayList<>();
		Iterator<Users> iterable = userDao.findAll().iterator();
		while (iterable.hasNext()) {
			Users entity = iterable.next();
			UserDto dto = new UserDto();
			dto.setUserName(entity.getUserName());
			dto.setRole(entity.getRole());
			ls.add(dto);
		}
		return ls;
	}

}
