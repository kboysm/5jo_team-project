package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.BoardFileVO;
import com.example.demo.bean.FileVO;
import com.example.demo.bean.GalleryBoardVo;

public interface GalleryBoardService {
	//총 게시물 수
	int boardCount();
	//전체 게시글 목록
	List<GalleryBoardVo>selectAll();
	//게시글 상세
	GalleryBoardVo boardDetail(int no);
	//일단 이미지 null이지만 insert
	int insertGalleryBoard(GalleryBoardVo galleryBoardvo);
	//갤러리 수정 구문
	int upadateGalleryBoard(GalleryBoardVo galleryBoardvo);
	//게시글 삭제
	int boardDelete(int no);
	//file test
	int fileInsertService(FileVO file);
	FileVO fileView(int no);
	
	//이미지 출력 testing
	List<BoardFileVO> imagePrint();
	//Page<BoardFileVO> imagePaging(Pageable pageable);
}
