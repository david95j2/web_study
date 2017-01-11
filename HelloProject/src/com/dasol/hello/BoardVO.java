package com.dasol.hello;

import java.util.Date;

public class BoardVO {
	private int num;
	private String username;
	private String title;
	private String memo;
	private Date time;
	private int hit;
	
	public BoardVO(String username, String title, String memo) {
		this.username = username;
		this.title = title;
		this.memo = memo;
	}

	public BoardVO(int num, String username, String title, String memo, 
			Date time, int hit) {
		this.num = num;
		this.username = username;
		this.title = title;
		this.memo = memo;
		this.time = time;
		this.hit = hit;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}


	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", username=" + username + ", title=" + title + ", memo=" + memo + ", time="
				+ time + ", hit=" + hit + "]";
	}
	
}
