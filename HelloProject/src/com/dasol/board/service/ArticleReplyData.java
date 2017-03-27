package com.dasol.board.service;

import java.util.Date;

import com.dasol.util.TransferDate;

public class ArticleReplyData {
	private int replyNo;
	private String nickname;
	private int memberId;
	private String commnet;
	private Date regdate;
	private int totReplyCnt;

	public ArticleReplyData(int replyNo, String nickname, int memberId, String commnet, Date regdate, int totReplyCnt) {
		this.replyNo = replyNo;
		this.nickname = nickname;
		this.memberId = memberId;
		this.commnet = commnet;
		this.regdate = regdate;
		this.totReplyCnt = totReplyCnt;
	}

	public int getReplyNo() {
		return replyNo;
	}


	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getCommnet() {
		return commnet;
	}

	public void setCommnet(String commnet) {
		this.commnet = commnet;
	}

	public int getTotReplyCnt() {
		return totReplyCnt;
	}

	public void setTotReplyCnt(int totReplyCnt) {
		this.totReplyCnt = totReplyCnt;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public String getTransferRegDate() {
		return TransferDate.getDate(regdate);
	}

}
