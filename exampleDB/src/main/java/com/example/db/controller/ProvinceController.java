package com.example.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db.model.MunicipalityModel;
import com.example.db.model.ProvinceModel;
import com.example.db.repository.ProvinceRepository;

@RestController
@RequestMapping("/province")
public class ProvinceController {

	@Autowired
	ProvinceRepository provinceRepository;
	
	@GetMapping("/get-all")
	public List<ProvinceModel> getAll(){
		return provinceRepository.findAll();
	}
	
	@PostMapping("/save")
	public ProvinceModel save(@RequestBody ProvinceModel model) {
		for(MunicipalityModel modelList : model.getMunicipalList()) {
			modelList.setProvince(model);
		}
		return provinceRepository.save(model);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		provinceRepository.deleteById(id);
		return "Deleted";
	}
}
