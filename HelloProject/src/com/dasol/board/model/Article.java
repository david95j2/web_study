package com.dasol.board.model;

import java.util.Date;

import com.dasol.util.TransferDate;

public class Article {
	private Integer number;
	private String title;
	private int replyCnt;
	private int likeCnt;
	private int readCnt;
	private Date regDate;
	private Date modDate;
	private Writer writer;

	public Article(Integer number, String title, int replyCnt, int likeCnt, int readCnt, Date regDate, Date modDate,
			Writer writer) {
		this.number = number;
		this.title = title;
		this.replyCnt = replyCnt;
		this.likeCnt = likeCnt;
		this.readCnt = readCnt;
		this.regDate = regDate;
		this.modDate = modDate;
		this.writer = writer;
	}

	public Integer getNumber() {
		return number;
	}

	public String getTitle() {
		return title;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public Date getRegDate() {
		return regDate;
	}
	
	public String getTransferRegDate() {
		return TransferDate.getDate(regDate);
	}

	public Date getModDate() {
		return modDate;
	}

	public Writer getWriter() {
		return writer;
	}

}
