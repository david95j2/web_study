package com.dasol.auth.service;

public class User {
	private String email;
	private boolean register_check;

	public User(String email, boolean register_check) {
		this.email = email;
		this.register_check = register_check;
	}

	public String getEmail() {
		return email;
	}

	public boolean isRegister_check() {
		return register_check;
	}
	
}
