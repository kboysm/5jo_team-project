package com.example.demo.dao;

public class smPageMaker {
	private int totalcount; //전체 게시물 개수
	private int pagenum; //현재 페이지 번호
	private int contentnum=10; //한 페이지에 몇개 표시할지
	private int startPage=1; //현재 페이지 블록의 시작 페이지
	private int endPage=5; //현재 페이지 블록의 마지막 페이지
	private boolean prev=false; //이전 페이지로 가는 화살표
	private boolean next; //다음 페이지로 가는 화살표
	private int currentblock; //현재 페이지 블록
	private int lastblock; //미지막 페이지 블록
	
	public void prevnext(int pagenum) {
		//현재 페이지가 첫번째 페이지 블록 안에 있으면
		if(pagenum>0 && pagenum<6) {//첫번째 페이지블록 관련
			setPrev(false);
			setNext(true);
		}//이전 페이지 블록으로 가는 화살표는 보이지 않게 된고 다음페이지로 가는 화살표는 보이게 된다.
		else if(getLastblock() == getCurrentblock())//마지막 페이지 블록관련
			{ //다음으로 넘어가는 화살표는 안보이게 이전으로 넘어가는 화살표는 보이게 한다.
			setPrev(true);
		setNext(false);
	}else {//첫번째 블록과 마지막 블록이 아닌 모든 블록은 양쪽으로 화살표가 존재해야 한다. 중간페이지블록에 관련
		setPrev(true);
		setNext(true);
	}
	}
	
	
	//전체 페이지 수를 계산하는 함수 
	// 125 /10 -> 12.5  0.5는 0보다 크기 때문에 +1되서 13페이지가 됨
	// 50/10 -> 5로 나눠 떨어지고 나머지는 0이기 때문에 ++되지 않음 즉 5페이지가 생성됨 
	public int calcpage(int totalcount ,int contentnum) {
		int totalpage = totalcount/ contentnum;
		if(totalcount%contentnum>0) {
			totalpage++;
		}
		
		return totalpage;
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
	public void setStartPage(int currentblock) { //페이지 블록을 구하는 함수
		//한페이지에 5개의 블록이 보이도록 함 
		//currentblock =1 일때 1,2,3,4,5
		// 2일때 6,7,8,9,10
		// 3일때 11,12,13,14,15
		//즉 페이지에 보여지는 페이지블록의 시작 페이지를 구하는거 같음 조건은 게시물의 개수가 150개 이하일때 위의 설명이 맞는것
		//게시물의 보여지는 페이지의 수가 10개라면 *10을 한후 -9를 해주면 된다.
		this.startPage = (currentblock*5)-4;
	}
	public int getEndPage() {
		return endPage;
	}
	//매개변수 : 마지막 페이지 블록번호 ,현재 페이지 블록 번호
	public void setEndPage(int getlastblock,int getcurrentblock) {
		if(getlastblock==getcurrentblock) {// 이 페이지 블록이 마지막 페이지이면
			this.endPage = calcpage(getTotalcount(), getContentnum());
		}//칼크 페이지를 통해서 마지막 페이지를 엔드페이지에 넣는다 즉 그 블록에서 endpage는 마지막 페이지가 된다.
		else {
			this.endPage =getStartPage()+4;
		}//그리고 if에 속하지 않는 모든 경우에는 시작 페이지 +4를 해준다. ex) 1 2 3 4 5
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
	
	//현재 페이지 블록
	//페이지 번호를 통해서 구한다.         //현재 페이지번호
	public void setCurrentblock(int pagenum) {
		//현재 페이지 번호가 1페이지 라면 1/5라면 0.2이므로 1을 더해주게 된다.
		this.currentblock = pagenum/5; //여기서는 결과값이 0이다 인트형이기때문이다 0페이지가 없으므로 if문으로
		if(pagenum%5>0) {//나머지가 0.2이므로 0보다 크기때문에 1을 더해준다 즉 첫번째 페이지 블록이 된다.
			this.currentblock++;
		}
	}
	//위의 계산식에서 현재 페이지가 8페이지라고 가정하면 8/5는 1.6이므로 1보다 크고 나머지도 0.6이므로 0보다 크기때문에
	//1+1이 되서 현재 2번째 페이지블록이라는 것을 알 수 있다.
	
	public int getLastblock() {
		return lastblock;
	}
	
	//마지막 페이지 블록
	public void setLastblock(int totalcount) {
		//10개씩 게시물을 가져오고 한페이지블록당 5개의 페이지가 출력된다.
		//한 페이지 블록당 50개 1~5페이지의 게시물의 게시글의 수는 50개 이다.
		//전체 게시글의 갯수를 50으로 나눠주게 되면 나머지가 나올거고 몫이 나올 것이다.
		//거기에 더하기를 하게되면 마지막 페이지 블록이 몇번인지를 알 수 있게 된다.
 		
		this.lastblock = totalcount/(5*this.contentnum);
		if(totalcount %(5*this.contentnum)>0) {
			this.lastblock++;
		}
		
		//전체 게시글이 125라고 했을때50으로 나누면 2.5 나머지가 0.5이므로 1이 더해져
		//페이지 블록이 3개가 존재하게 된다.
	}
	
	
}
