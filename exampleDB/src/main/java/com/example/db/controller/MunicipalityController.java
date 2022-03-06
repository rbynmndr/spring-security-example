package com.example.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.db.customquery.Custom;
import com.example.db.model.MunicipalityModel;
import com.example.db.repository.MunicipalityRepository;

@RestController
public class MunicipalityController {

	@Autowired
	MunicipalityRepository municipalityRepository;
	
	@Autowired
	Custom customQue;
	
	@GetMapping("/munici")
	public List<MunicipalityModel> getAll(){
		return municipalityRepository.findAll();
	}
	
	@GetMapping("/munici/{id}")
	public List<MunicipalityModel> getByProvince(@PathVariable int id){
		return municipalityRepository.findByProvince(id);
	}
	
	@GetMapping("/muni/{id}")
	public List<MunicipalityModel> getOnlyName(@PathVariable int id){
		return customQue.getOnlyName(id);
	}
	
	@GetMapping("/muni/getbyname/{name}")
	public List<MunicipalityModel> getByName(@PathVariable String name){
		return municipalityRepository.findByMuniciName(name);
	}

}
