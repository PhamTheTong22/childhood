package com.lifung.springbootlifung.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lifung.springbootlifung.entity.Users;
import com.lifung.springbootlifung.repositories.UserDao;

/**
 * This is UserDetail class to get information of user
 * 
 * @author TongPT1
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserDao userDao;

	@Autowired
	public UserDetailsServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Users user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(user.getUserName(), user.getPassword(), getGrantedAuthorities(Arrays.asList(user.getRole())));
	}
	
	/**
	 * Get all roles of a user
	 * 
	 * @param privileges
	 * @return roles of user
	 */
	private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
}
