package com.lifung.springbootlifung.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifung.springbootlifung.config.ValidateToken;
import com.lifung.springbootlifung.model.ResponseDto;
import com.lifung.springbootlifung.model.UserDto;
import com.lifung.springbootlifung.service.UserDetailsServiceImpl;
import com.lifung.springbootlifung.service.UserService;

/**
 * Handle all apis and authentication
 * 
 * @author TongPT1
 *
 */
@RestController
@RequestMapping("/api")
public class MainController {

	private AuthenticationManager authenticationManager;

	private ValidateToken validateToken;

	private UserService userService;

	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	public MainController(AuthenticationManager authenticationManager, ValidateToken validateToken,
			UserService userService, UserDetailsServiceImpl userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.validateToken = validateToken;
		this.userService = userService;
		this.userDetailsService = userDetailsService;
	}
	
	/**
	 * Create a new user
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/register")
	public ResponseEntity<?> createUsers(@RequestBody UserDto userDto) throws Exception {
		userService.createUser(userDto);
		return ResponseEntity.ok(200);
	}
	
	/**
	 * Check login of user
	 * 
	 * @param userDto
	 * @return token if successful else throw error
	 * @throws Exception
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto userDto) throws Exception {
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUserName());
		authenticate(userDto.getUserName(), userDto.getPassword(), userDetails);
		final String token = validateToken.generateToken(userDetails);
		return ResponseEntity.ok(new ResponseDto(token));
	}
	
	/**
	 * Get profile of user
	 * 
	 * @param principal
	 * @return information of user
	 * @throws Exception
	 */
	@PermitAll
	@GetMapping("/profile")
	public ResponseEntity<?> userProfile(Principal principal) throws Exception {
		final UserDto user = userService.getUser(principal.getName());
		return ResponseEntity.ok(user);
	}
	
	/**
	 * Get list user in application
	 * 
	 * @return list users
	 * @throws Exception
	 */
	@RolesAllowed("Admin")
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() throws Exception {
		final List<UserDto> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	/**
	 * Authenticate for any user
	 * 
	 * @param userName
	 * @param password
	 * @param userDetails
	 * @throws Exception
	 */
	private void authenticate(final String userName, final String password, final UserDetails userDetails)
			throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userName, password, userDetails.getAuthorities()));
		} catch (Exception e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
