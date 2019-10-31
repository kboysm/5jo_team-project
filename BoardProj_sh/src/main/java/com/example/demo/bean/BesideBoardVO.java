package com.example.demo.bean;

public class BesideBoardVO {
	int no;
	int upNo;
	int downNo;
	
	
	public BesideBoardVO() {
		super();
	}
	public BesideBoardVO(int no, int upNo, int downNo) {
		super();
		this.no = no;
		this.upNo = upNo;
		this.downNo = downNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getUpNo() {
		return upNo;
	}
	public void setUpNo(int upNo) {
		this.upNo = upNo;
	}
	public int getDownNo() {
		return downNo;
	}
	public void setDownNo(int downNo) {
		this.downNo = downNo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + downNo;
		result = prime * result + no;
		result = prime * result + upNo;
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
		BesideBoardVO other = (BesideBoardVO) obj;
		if (downNo != other.downNo)
			return false;
		if (no != other.no)
			return false;
		if (upNo != other.upNo)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BesideBoardVO [no=" + no + ", upNo=" + upNo + ", downNo=" + downNo + "]";
	}
	
	
}
