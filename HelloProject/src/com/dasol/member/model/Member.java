package com.dasol.member.model;

import java.util.Date;

public class Member {
	private String email;
	private String password;
	private Date regdate;
	private String profile_image;

	public Member(String email, String password, Date regdate, String profile_image) {
		this.email = email;
		this.password = password;
		this.regdate = regdate;
		this.profile_image = profile_image;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Date getRegdate() {
		return regdate;
	}

	public String getProfile_image() {
		return profile_image;
	}

}
