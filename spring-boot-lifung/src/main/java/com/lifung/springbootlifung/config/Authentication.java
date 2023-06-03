package com.lifung.springbootlifung.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * This class will be to throws error if Unauthorized
 * 
 * @author TongPT1
 *
 */

@Component
public class Authentication implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    /*
     * This method throws error if Unauthorized
     * 
     * @param request is HttpServletRequest
     * @param response is HttpServletResponse
     * @param authException is AuthenticationException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}