package com.example.db.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.db.model.RoleModel;
import com.example.db.model.UserModel;
import com.example.db.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//retrieve user from database.
		UserModel userModel = userRepository.findByUserName(username);
		
		if(userModel == null) {
			throw new UsernameNotFoundException("User not found in database");
		}
		else {
			List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			for(RoleModel role : userModel.getUserRole()) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
				authorities.add(authority);
			}
			User user = new User(userModel.getUserName(), userModel.getUserPassword(), authorities);
			return user;
		}
	}

}
