package com.dasol.member.service;

import java.util.Map;

public class JoinRequest {
	private String email;
	private String password;
	private String confirmPassword;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, email, "email");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, confirmPassword, "confirmPassword");
		if(!errors.containsKey("confirmPassword")) {
			if(!comparePassword()) {
				errors.put("noMatch", Boolean.TRUE);
			}
		}
	}
	
	public boolean comparePassword() {
		return password != null && password.equals(confirmPassword);
	}
	
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}
	
}
