package com.example.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.db.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{

	UserModel findByUserName(String name);
}
