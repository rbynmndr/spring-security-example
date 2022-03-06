package com.example.db.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db.model.RoleModel;
import com.example.db.model.UserModel;
import com.example.db.repository.RoleRepository;
import com.example.db.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/user-list")
	public List<UserModel> getAll(){
		return userRepository.findAll();
	}
	
	@PostMapping("/save")
	@Transactional
	public UserModel save(@RequestBody UserModel model) {
		List<RoleModel> roleModel = new ArrayList<RoleModel>();
		for(RoleModel models : model.getUserRole()) {
			RoleModel dbModel = roleRepository.findByRoleName(models.getRoleName());
			if(dbModel == null) {
				roleModel.add(models);
			}
			else {
				roleModel.add(dbModel);
			}
		}
		model.setUserRole(roleModel);
		model.setUserPassword(passwordEncoder.encode(model.getUserPassword()));
		return userRepository.save(model);
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
}
