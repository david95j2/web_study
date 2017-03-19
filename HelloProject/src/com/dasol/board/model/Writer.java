package com.dasol.board.model;

public class Writer {
	private Integer memberId;
	private String nickname;
	private String profileImage;

	public Writer(Integer memberId, String nickname, String profileImage) {
		this.memberId = memberId;
		this.nickname = nickname;
		this.profileImage = profileImage;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public String getNickname() {
		return nickname;
	}

	public String getProfileImage() {
		return profileImage;
	}

}
