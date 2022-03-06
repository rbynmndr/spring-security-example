package com.example.db.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			String header = request.getHeader(HttpHeaders.AUTHORIZATION);
			if(header != null && header.startsWith("Bearer ")) {
				try {
					String token = header.substring("Bearer ".length());			//Bearer eYdjfakjjsfde.sdfkahd.sdfl
					JWTVerifier verifier = JWT.require(Algorithm.HMAC512("SECRET".getBytes())).build();
					DecodedJWT decodedJwt = verifier.verify(token);
					String username = decodedJwt.getSubject();
					List<String> roles = decodedJwt.getClaim("roles").asList(String.class);
					
					List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
					for(SimpleGrantedAuthority authority : authorities) {
						roles.add(authority.getAuthority());
					}
					
					UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(userToken);
					filterChain.doFilter(request, response);
				}catch(Exception e) {
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					Map<String, String> msg = new HashMap<String, String>();
					msg.put("error", e.getMessage());
					new ObjectMapper().writeValue(response.getOutputStream(), msg);
				}
			}
			else {
				filterChain.doFilter(request, response);
			}
	}

}
