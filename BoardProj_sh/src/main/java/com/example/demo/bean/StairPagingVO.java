package com.example.demo.bean;

public class StairPagingVO {
	private int nowPage, startPage, endPage, totalPage, cntPerBlock, cntPerPage, start, end, total;

	public StairPagingVO() {}
	
	public StairPagingVO(int nowPage, int total) {
		super();
		this.nowPage = nowPage;
		this.total = total;
		cntPerBlock=5;
		cntPerPage=10;
		pageSetting();
	}
	
	public StairPagingVO(int nowPage, int startPage, int endPage, int totalPage, int cntPerBlock, int cntPerPage, int start,
			int end, int total) {
		super();
		this.nowPage = nowPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totalPage = totalPage;
		this.cntPerBlock = cntPerBlock;
		this.cntPerPage = cntPerPage;
		this.start = start;
		this.end = end;
		this.total = total;
	}
	
	public void pageSetting() {
		totalPage=(total-1)/cntPerPage+1;
		startPage=(nowPage-1)-(nowPage-1)%cntPerBlock+1;
		endPage=startPage+cntPerBlock-1;
		if(endPage>totalPage)
			endPage=totalPage;
		start=(nowPage-1)*cntPerPage+1;
		end=start+cntPerPage-1;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCntPerBlock() {
		return cntPerBlock;
	}

	public void setCntPerBlock(int cntPerBlock) {
		this.cntPerBlock = cntPerBlock;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cntPerBlock;
		result = prime * result + cntPerPage;
		result = prime * result + end;
		result = prime * result + endPage;
		result = prime * result + nowPage;
		result = prime * result + start;
		result = prime * result + startPage;
		result = prime * result + total;
		result = prime * result + totalPage;
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
		StairPagingVO other = (StairPagingVO) obj;
		if (cntPerBlock != other.cntPerBlock)
			return false;
		if (cntPerPage != other.cntPerPage)
			return false;
		if (end != other.end)
			return false;
		if (endPage != other.endPage)
			return false;
		if (nowPage != other.nowPage)
			return false;
		if (start != other.start)
			return false;
		if (startPage != other.startPage)
			return false;
		if (total != other.total)
			return false;
		if (totalPage != other.totalPage)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PagingVO [nowPage=" + nowPage + ", startPage=" + startPage + ", endPage=" + endPage + ", totalPage="
				+ totalPage + ", cntPerBlock=" + cntPerBlock + ", cntPerPage=" + cntPerPage + ", start=" + start
				+ ", end=" + end + ", total=" + total + "]";
	}
	
	
	
}
