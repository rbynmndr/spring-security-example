package com.example.db.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "municipality_tbl")
public class MunicipalityModel extends Auditable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "municipality_id")
	private int municiId;
	
	@Column(name = "municipality_name")
	private String municiName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "province_id")
	@JsonIgnoreProperties("municipalList")
	private ProvinceModel province;

	public int getMuniciId() {
		return municiId;
	}

	public void setMuniciId(int municiId) {
		this.municiId = municiId;
	}

	public String getMuniciName() {
		return municiName;
	}

	public void setMuniciName(String municiName) {
		this.municiName = municiName;
	}

	public ProvinceModel getProvince() {
		return province;
	}

	public void setProvince(ProvinceModel province) {
		this.province = province;
	}
	
}
