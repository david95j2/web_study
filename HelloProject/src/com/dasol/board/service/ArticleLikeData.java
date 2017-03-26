package com.dasol.board.service;

public class ArticleLikeData {
	private Integer likeNo;
	private Integer memberId;
	private String nickname;
	private Integer totLikeCnt;

	public ArticleLikeData(Integer likeNo, Integer memberId, String nickname, Integer totLikeCnt) {
		this.likeNo = likeNo;
		this.memberId = memberId;
		this.nickname = nickname;
		this.totLikeCnt = totLikeCnt;
	}

	public Integer getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(Integer likeNo) {
		this.likeNo = likeNo;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getTotLikeCnt() {
		return totLikeCnt;
	}

	public void setTotLikeCnt(Integer totLikeCnt) {
		this.totLikeCnt = totLikeCnt;
	}

}
