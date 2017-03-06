package com.dasol.member.model;

import java.util.Date;

public class Member {
	private int memberId;
	private String email;
	private String password;
	private String nickname;
	private Date regdate;
	private String profileImage;
	private String registerCode;
	private boolean registerCheck;
	
	public Member(String email, String password, String nickname, Date regdate, 
			String profileImage, String registerCode, boolean registerCheck) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.regdate = regdate;
		this.profileImage = profileImage;
		this.registerCode = registerCode;
		this.registerCheck = registerCheck;
	}
	
	public Member(int memberId, String email, String password, String nickname, Date regdate, 
			String profileImage, String registerCode, boolean registerCheck) {
		this.memberId = memberId;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.regdate = regdate;
		this.profileImage = profileImage;
		this.registerCode = registerCode;
		this.registerCheck = registerCheck;
	}

	public int getMemberId() {
		return memberId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getNickname() {
		return nickname;
	}

	public Date getRegdate() {
		return regdate;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public boolean isRegisterCheck() {
		return registerCheck;
	}
	
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}

}
