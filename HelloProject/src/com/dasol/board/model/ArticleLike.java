package com.dasol.board.model;

public class ArticleLike {
	private Integer number;
	private Integer memberId;
	private String nickname;
	private Integer articleNo;

	public ArticleLike(Integer number, Integer memberId, String nickname, Integer articleNo) {
		this.number = number;
		this.memberId = memberId;
		this.nickname = nickname;
		this.articleNo = articleNo;
	}

	public Integer getNumber() {
		return number;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public String getNickname() {
		return nickname;
	}

	public Integer getArticleNo() {
		return articleNo;
	}
	
	public boolean isLikeIt(Integer memberId) {
		return this.memberId == memberId ? true : false;
	}

}
