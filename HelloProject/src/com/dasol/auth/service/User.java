package com.dasol.auth.service;

public class User {
	private int memberId;
	private String email;
	private boolean registerCheck;
	private String rememberToken;
	private boolean hasPassword;

	public User(int memberId, String email, boolean registerCheck, String rememberToken,
			boolean hasPassword) {
		this.memberId = memberId;
		this.email = email;
		this.registerCheck = registerCheck;
		this.rememberToken = rememberToken;
		this.hasPassword = hasPassword;
	}

	public int getMemberId() {
		return memberId;
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
	
	public boolean getHasPassword() {
		return hasPassword;
	}
	
	public void setHasPassword(boolean check){
		this.hasPassword = check;
	}
}
