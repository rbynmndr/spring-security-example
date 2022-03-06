package com.example.db.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "province_tbl")
public class ProvinceModel extends Auditable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "province_id")
	private int id;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "province_name")
	private String ProvinceName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "province")
	@JsonIgnoreProperties("province")
	private List<MunicipalityModel> municipalList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvinceName() {
		return ProvinceName;
	}

	public void setProvinceName(String provinceName) {
		ProvinceName = provinceName;
	}

	public List<MunicipalityModel> getMunicipalList() {
		return municipalList;
	}

	public void setMunicipalList(List<MunicipalityModel> municipalList) {
		this.municipalList = municipalList;
	}
	
}
