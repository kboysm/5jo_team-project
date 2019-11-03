package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.FileBoardVO;
import com.example.demo.bean.UpdateFileVO;

public interface FileBoardService {

	List<FileBoardVO> getFileBoardList();
	
	FileBoardVO fileBoardDetail(int b_no);
	
	int fileBoardInsert(FileBoardVO fileBoard);
	
	int fileBoardUpdate(FileBoardVO fileBoard);

	int fileBoardDelete(int bno);
	
	int fileInsertService(UpdateFileVO file);
	
	UpdateFileVO fileDetail(int b_no);
	
}
