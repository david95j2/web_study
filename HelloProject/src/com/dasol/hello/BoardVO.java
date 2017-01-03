package com.dasol.hello;

import java.sql.Date;

public class BoardVO {
	private int num;
	private String username;
	private String title;
	private String memo;
	private Date time;
	private int hit;
	private int ref;
	private int indent;
	private int step;
	
	public BoardVO(String username, String title, String memo) {
		this.username = username;
		this.title = title;
		this.memo = memo;
	}

	public BoardVO(int num, String username, String title, String memo, 
			Date time, int hit, int ref, int indent,
			int step) {
		this.num = num;
		this.username = username;
		this.title = title;
		this.memo = memo;
		this.time = time;
		this.hit = hit;
		this.ref = ref;
		this.indent = indent;
		this.step = step;
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

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", username=" + username + ", title=" + title + ", memo=" + memo + ", time="
				+ time + ", hit=" + hit + ", ref=" + ref + ", indent=" + indent + ", step=" + step + "]";
	}
	
}
