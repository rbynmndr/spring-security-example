package com.example.db.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthencationFilter extends UsernamePasswordAuthenticationFilter{

	AuthenticationManager authenticationManager;
	
	public JwtAuthencationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(userToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		List<String> roleList = new ArrayList<String>();
		for(GrantedAuthority authority : user.getAuthorities()) {
			roleList.add(authority.getAuthority());
		}
		Date dateToExpire = new Date(System.currentTimeMillis() + 1*60*1000);
		String token = JWT.create()
						.withSubject(user.getUsername())
						.withExpiresAt(dateToExpire)
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", roleList)
						.sign(Algorithm.HMAC512("SECRET".getBytes()));
		
		response.setHeader("access-token", token);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("access-token", token);
		msg.put("msg", "Use this token for further request.");
		new ObjectMapper().writeValue(response.getOutputStream(), msg);
	}

	
	
}
