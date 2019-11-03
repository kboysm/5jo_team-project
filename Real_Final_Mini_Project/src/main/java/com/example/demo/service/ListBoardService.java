package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.ListBoardVO;

public interface ListBoardService {

	//게시글 갯수 
		public int countContent();
		
		//조회수
		public void countView(int idx);
		
		//게시글 목록
		public List<ListBoardVO> viewList();
		
		//게시글 상세
		public ListBoardVO viewContent(int idx) ;
		
		//게시글 작성
		public int insert(ListBoardVO vo);

		//게시글 수정
		public int update(ListBoardVO vo);

		//게시글 삭제
		public int delete(int idx);
		
		//게시물 검색
		public List<ListBoardVO> findByTitle(String title);
		public List<ListBoardVO> findByWriter(String writer);

		
}
