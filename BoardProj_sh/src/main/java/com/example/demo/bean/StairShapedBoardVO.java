package com.example.demo.bean;

import java.util.Date;

public class StairShapedBoardVO {
	int level;
	int no;
	int grpno;
	int prntno;
	String title;
	String writer;
	String content;
	Date regdate;
	int hits;
	public StairShapedBoardVO() {}
	
	public StairShapedBoardVO(String title, String writer, String content) {
		super();
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	public StairShapedBoardVO(int level, int no, int grpno, int prntno, String title, String writer, String content,
			Date regdate, int hits) {
		super();
		this.level = level;
		this.no = no;
		this.grpno = grpno;
		this.prntno = prntno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.hits = hits;
	}

	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getGrpno() {
		return grpno;
	}

	public void setGrpno(int grpno) {
		this.grpno = grpno;
	}

	public int getPrntno() {
		return prntno;
	}

	public void setPrntno(int prntno) {
		this.prntno = prntno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + no;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StairShapedBoardVO other = (StairShapedBoardVO) obj;
		if (no != other.no)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StairShapedBoardVO [level=" + level + ", no=" + no + ", grpno=" + grpno + ", prntno=" + prntno
				+ ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate=" + regdate
				+ ", hits=" + hits + "]";
	}

	
	
	
}
