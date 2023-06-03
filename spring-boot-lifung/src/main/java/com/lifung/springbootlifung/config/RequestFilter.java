package com.lifung.springbootlifung.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lifung.springbootlifung.service.UserDetailsServiceImpl;

/**
 * This class filters to a request from client
 * 
 * @author TongPT1
 *
 */
@Component
public class RequestFilter extends OncePerRequestFilter {

	private UserDetailsServiceImpl userDetailsService;

	private ValidateToken token;

	@Autowired
	public RequestFilter(final UserDetailsServiceImpl userDetailsService, final ValidateToken token) {
		this.userDetailsService = userDetailsService;
		this.token = token;
	}

	/**
	 *  This method after checking authentication to redirect to controller
	 *     
     * @param request is HttpServletRequest to handle request
     * @param response is HttpServletResponse to return response
     * @param chain is FilterChain
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String jwtToken = request.getHeader("Authorization");

		String username = null;
		if (jwtToken != null) {
			try {
				jwtToken = jwtToken.replace("Bearer ", "");
				username = token.getUsernameFromToken(jwtToken);
			} catch (Exception e) {
				logger.error("Unable to get JWT Token: " + e.toString());
			}
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if (token.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}
