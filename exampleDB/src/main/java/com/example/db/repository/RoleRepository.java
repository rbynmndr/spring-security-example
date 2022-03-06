package com.example.db.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.db.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Long>{

	RoleModel findByRoleName(String name);
}
