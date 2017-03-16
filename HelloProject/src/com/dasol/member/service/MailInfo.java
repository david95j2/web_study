package com.dasol.member.service;

public class MailInfo {
	private String email;
	private String registerCode;
	private String content;

	public MailInfo(String email, String registerCode) {
		this.email = email;
		this.registerCode = registerCode;
	}

	public String getEmail() {
		return email;
	}

	public String getRegisterCode() {
		return registerCode;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setRegisterContent() {
		System.out.println("mailInfo="+registerCode);
		this.content = "<a href="
				+ "'http://localhost:8080/helloproject/register.do?email="+email+"&registerCode="+registerCode+"'>"
				+ "지금 이곳을 눌러 바로 인증하세요.</a>";
	}
	
	public void setPasswordContent() {
		this.content = "";
	}

}
