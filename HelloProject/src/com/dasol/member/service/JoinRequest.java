package com.dasol.member.service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		subValidate(errors);
	}
	
	private void subValidate(Map<String, Boolean> errors) {
		if(!errors.containsKey("email")) {
			checkEmailRegEx(errors, email, "emailRegEx");
		}
		
		if(!errors.containsKey("password")) {
			checkPasswordRegEx(errors, password, "passwordRegEx");
		}
		
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
	
	private void checkEmailRegEx(Map<String, Boolean> errors, String value, String errorName) {
		Pattern pattern = Pattern.compile("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$");
		Matcher matcher = pattern.matcher(value);
		if(!matcher.matches()) {
			errors.put(errorName, Boolean.TRUE);
		}
	}
	
	private void checkPasswordRegEx(Map<String, Boolean> errors, String value, String errorName) {
		Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,16}$");
		Matcher matcher = pattern.matcher(value);
		if(!matcher.matches()) {
			errors.put(errorName, Boolean.TRUE);
		}
	}
	
}
