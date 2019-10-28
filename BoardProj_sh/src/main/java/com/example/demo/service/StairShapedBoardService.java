package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.StairShapedBoardVO;

public interface StairShapedBoardService {

	List<StairShapedBoardVO> getBoardListEq(int start, int end, String category, String search);
	List<StairShapedBoardVO> getBoardListLk(int start, int end, String category, String search);
	List<StairShapedBoardVO> getBoardList(int start, int end);
	StairShapedBoardVO getBoard(int no);
	int countBoard();
	int countBoardEq(String category, String search);
	int countBoardLk(String category, String search);
	int insertBoard(StairShapedBoardVO board);

}
