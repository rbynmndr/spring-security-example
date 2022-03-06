package com.example.db.model;

public class CustomModel {
	private int id;
	private String name;
	private String address;
	
	public CustomModel() {
		this.id = builder.id;
		this.name = builder.name;
		this.address = builder.address;
	}
	
	public static class builder {
		public static int id;
		public static String name;
		public static String address;
		
		public builder id(int id) {
			this.id = id;
			return this;
		}
		public builder name(String name) {
			this.name = name;
			return this;
		}
		public builder address(String address) {
			this.address = address;
			return this;
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
	
}
