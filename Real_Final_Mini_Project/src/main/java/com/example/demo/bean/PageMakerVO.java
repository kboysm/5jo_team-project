package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageMakerVO {

	private int totalcount;//전체 게시물 갯수
	private int contentnum=10;//한 페이지에 몇 개 표시할지
	private int startPage=1;//현재 페이지 블록의 시작 페이지
	private int endPage=5;//실제 마지막 페이지
	private boolean prev=false;//이전 페이지로 가는 화살표
	private boolean next;//다음 페이지로 가는 화살표
	private int pagenum;//현재 페이지 번호
	private int currentblock;//현재 페이지 블록
	private int lastblock;//마지막 페이지 블록
	private int totalPage; //현재 페이지 블록에 존재하는 마지막 페이지

	public void prevnext(int pagenum) {
		if(pagenum>0 && pagenum<6) {
			if(totalPage > endPage) {
				setPrev(false);
				setNext(true);				
			} else if (totalPage <= endPage) {
				setPrev(false);
				setNext(false);				
			}
		} else if(getLastblock() == getCurrentblock()) {
			setPrev(true);
			setNext(false);
		} else {
			setPrev(true);
			setNext(true);
		}
	}
	
	/*
	 * 현재 게시판에 몇 페이지까지 표시 할 것인지 계산 할 함수가 필요함 예) 전체 게시글 125개, 한 페이지당 출력할 게시글 수 10개 =>
	 * 125/10=12.5 나머지가 0보다 크기 때문에 totalpage++ 적용되어 13이 출력. 총 13페이지.
	 */
	public void setTotalPage(int totalcount, int contentnum) {
		totalPage = totalcount/contentnum;
		if(totalcount%contentnum > 0) totalPage++;
		//this.totalPage = totalpage;
		//return this.totalPage;
	}
	
	public void setStartPage(int currentblock) {
		this.startPage = (currentblock*5)-4;
	}
	
	/*
	 * 마지막 페이지 설정 예) 전체 게시글 128개, 한 페이지당 출력할 게시글 수 10개 => 13페이지가 필요, 마지막 페이지는 13.
	 * 페이지 당 들어갈 게시글 수가 다 채워져 있다면 시작페이지+4만 하면 되는데, 마지막 페이지는 예외(다 채워져 있지 않을 수 있음).
	 * 따라서 이런 경우에만 마지막 페이지를 따로 처리해줘야 하는데, 위 예에서 보면 나머지가 나올 경우 +1을 하면 되기 때문에
	 * calpage() 적용. 그렇지 않은 경우는 '시작페이지+4'만 해주면 되기 때문에 처리.
	 */
	public void setEndPage(int getlastblock, int getcurrentblock) {
		if(getlastblock == getcurrentblock){
			this.endPage = totalPage;
		} else {
			this.endPage = getStartPage()+4;
		}
	}
	
	/*
	 * '현재 페이지 블록'은 페이지 번호를 통해 구함.
	 * 현재 3p에 있는 경우, '현재 페이지 블록'은 1임. 9p인 경우 2, 10p인 경우도 2.
	 * 현재 페이지가 5로 나눴을 때 나머지가 생기면 '현재 페이지 블록'에 +1 적용.
	 * 나눠 떨어지면 '현재 페이지 블록'에서 가장 마지막 페이지 번호이기 때문에 if문 빠져나와 적용 안 됨.
	 */
	public void setCurrentblock(int pagenum) {
		this.currentblock = pagenum/5;
		if(pagenum%5 > 0) {
			this.currentblock++;
		}
	}
	
	/*
	 * '페이지 블록'은 예를 들어 총 게시글이 95개가 있고, 한 페이지에 10개씩 나오게 하면, 총 페이지 수는 10개가 됨. 
	 * 이 페이지 수를 균등하게 나눈 게 '페이지 블록'. 페이지 수를 5개씩 나오게 한다면, 한 페이지 블록 당 나올 페이지 수는 5개. 
	 * 이 경우, 총 페이지 블록 수는 2개.
	 * 한 페이지에 10개씩 보이게 할 것이고(contentnum=10) totalcount=95인 경우, lastblock은 1.9 => 1
	 * 본디 lastblock 값이 나머지가 나왔으니 +1을 자동으로 하면 됨.
	 */
	
	public void setLastblock(int totalcount) {
		this.lastblock = totalcount/(5*this.contentnum);
		if(totalcount%(5*this.contentnum) > 0) {
			this.lastblock++;
		}
	}
}