package com.dasol.board.service;

import java.util.List;

import com.dasol.board.model.ArticleReply;

public class ReplyData {
	private int totCnt;
	private List<ArticleReply> replyList;

	public ReplyData(int totCnt, List<ArticleReply> replyList) {
		this.totCnt = totCnt;
		this.replyList = replyList;
	}

	public int getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}

	public List<ArticleReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<ArticleReply> replyList) {
		this.replyList = replyList;
	}

}
