package com.example.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.db.model.MunicipalityModel;

public interface MunicipalityRepository extends JpaRepository<MunicipalityModel, Integer>{
	
	@Query(value = "select m.* from municipality_tbl m where m.province_id = :id", nativeQuery = true)
	List<MunicipalityModel> findByProvince( int id);
	
	List<MunicipalityModel> findByMuniciName(String name);  //findByMuniciName  
	
}
