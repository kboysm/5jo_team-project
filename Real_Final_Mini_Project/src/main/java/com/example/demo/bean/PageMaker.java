package com.example.demo.bean;

public class PageMaker {
	//참조 사이트: 
	//초기화 세팅
	private int totalcount;//전체 게시물 개수
	private int pagenum;//전체 페이지 번호
	private int contentnum=10;//한 페이지 몇개 보일지
	private int startPage=1;//현재 페이지 블록의 시작페이지
	private int endPage=5;//현재 페이지 블록의 끝 페이지
	private boolean prev =false;//이전 페이지 화살표
	private boolean next;//다음 페이지 화살표
	private int currentblock;//현재 페이지 블록
	private int lastblock;//끝 페이지 블록
	private int totalpage;
	
	
	public void prevnext(int pagenum) {
			if(pagenum>0 &&pagenum<6) {
				if(totalpage > endPage) {
					setPrev(false);//첫번째 블럭일 경우 이전 페이지 안보이게 함
					setNext(true); //다음페이지로 가는건 보이게 함
				} else if(totalpage <= endPage) {
					setPrev(false);//첫번째 블럭일 경우 이전 페이지 안보이게 함
					setNext(false); //다음페이지로 가는건 보이게 함
				}
			}else if(getLastblock() == getCurrentblock()) {
				setPrev(true);
				setNext(false);
			}else {
				setPrev(true);
				setNext(true);
			}
	}
	//전체 페이지 수를 계산하는 함수
	public void setTotalpage(int totalcount, int contentnum) {
		// 한 페이지 10개씩 보여주게 된다면
				// 125/10 =>12.5
				// 13페이지
				
				//50 /10 =>5
				//5페이지
				totalpage = totalcount/contentnum;
				if(totalcount%contentnum>0) {
					totalpage++;
				}
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getPagenum() {
		return pagenum;
	}
	
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public int getContentnum() {
		return contentnum;
	}
	public void setContentnum(int contentnum) {
		this.contentnum = contentnum;
	}
	public int getStartPage() {
		return startPage;
	}
	
	//시작 페이지(블록을 가지고 구할 수 있다.)
	public void setStartPage(int currentblock) {
		this.startPage = (currentblock*5)-4;
		//1 / 1 2 3 4 5 (보여주는 페이지 블락 갯수5개 이므로 *5)
		//2 / 6 7 8 9 10
		//3 / 11 12 13
	}
	public int getEndPage() {
		return endPage;
	}
	//마지막 페이지(첫 페이지 +4 해주면 끝페이지가 맞음)
	//마지막엔 계산이 달라질 수 있음 (5개를 꽉 채우지 않을 수 있으니까)
	public void setEndPage(int getlastblock, int getcurrentblock) {
		if(getlastblock == getcurrentblock) {
			this.endPage = totalpage;
		}else {
			//한 페이지당 5개씩만 출력하기 위해 +4
			this.endPage = getStartPage()+4;
		}
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getCurrentblock() {
		return currentblock;
	}
	//현재 페이지 블록 수정
	public void setCurrentblock(int pagenum) {
		//페이지 번호를 통해서 구한다
		//페이지 번호/ 페이지 그룹 안의 페이지 개수
		//1p 1/5 => 0.2(0) +1 => 페이지 블록 1
		//3p 3/5 => 0.XX(0) +1 => 페이지 블록 1
		//8p 8/5 => 1.6 (1) +1 => 페이지 블록 2
		
		this.currentblock = pagenum/5;
		if(pagenum%5>0) {
			this.currentblock++;
		}
	}
	//마지막 페이지 수정
	public int getLastblock() {
		return lastblock;
	}
	//페이지 블록이 몇개인 지 알 수 있다.
	public void setLastblock(int totalcount) {
		//10, 5 = > 10 * 5 => 50
		//125/50 => 2.5[나머지가 0보다 크니까 페이지 블록이 3까지 존재]
		//3
		this.lastblock = totalcount/(5*this.contentnum);
		if(totalcount %(5* this.contentnum)>0) {
			this.lastblock++;
		}
	}
	
	
	
}
