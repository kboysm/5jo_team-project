package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.FileBoardVO;
import com.example.demo.bean.UpdateFileVO;

@Mapper
public interface FileBoardMapper {

	List<FileBoardVO> getFileBoardList();
	FileBoardVO fileBoardDetail(int b_no);
	int fileBoardInsert(FileBoardVO fileBoard);
	int fileBoardUpdate(FileBoardVO fileBoard);
	int fileBoardDelete(int bno);
	
	//파일 업로드&다운로드
	int fileInsert(UpdateFileVO file);
	UpdateFileVO fileDetail(int b_no);
	
}