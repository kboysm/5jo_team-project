package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.BoardFileVO;
import com.example.demo.bean.FileVO;
import com.example.demo.bean.GalleryBoardVo;
import com.example.demo.mapper.GalleryBoardMapper;

@Service("GalleryBoardService")
public class GalleryBoardServiceImpl implements GalleryBoardService{

	@Autowired
	GalleryBoardMapper galleryBoardMapper;

	@Override
	public int boardCount() {
		return galleryBoardMapper.boardCount();
	}

	@Override
	public List<GalleryBoardVo> selectAll() {
		return galleryBoardMapper.selectAll();
	}

	@Override
	public int insertGalleryBoard(GalleryBoardVo galleryBoardvo) {
		return galleryBoardMapper.insertGalleryBoard(galleryBoardvo);
	}

	@Override
	public int upadateGalleryBoard(GalleryBoardVo galleryBoardvo) {
		return galleryBoardMapper.upadateGalleryBoard(galleryBoardvo);
	}

	@Override
	public GalleryBoardVo boardDetail(int no) {
		galleryBoardMapper.increaseCount(no);
		return galleryBoardMapper.boardDetail(no);
	}

	@Override
	public int boardDelete(int no) {
		return galleryBoardMapper.boardDelete(no);
	}
	
	@Override
	public int fileInsertService(FileVO file) {
		return galleryBoardMapper.fileInsert(file);
	}
	@Override
	public FileVO fileView(int no) {
		return galleryBoardMapper.fileView(no);
	}

	@Override
	public List <BoardFileVO> imagePrint() {
		return galleryBoardMapper.imagePrint();
	}
}
