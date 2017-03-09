package com.dasol.auth.service;

public class User {
	private String email;
	private boolean registerCheck;
	private String rememberToken;

	public User(String email, boolean registerCheck, String rememberToken) {
		this.email = email;
		this.registerCheck = registerCheck;
		this.rememberToken = rememberToken;
	}

	public String getEmail() {
		return email;
	}

	public boolean isRegister_check() {
		return registerCheck;
	}
	
	public String getRememberToken() {
		return rememberToken;
	}
	
}
