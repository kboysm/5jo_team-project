package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.BesideBoardVO;
import com.example.demo.bean.StairShapedBoardVO;

public interface StairShapedBoardService {

	List<StairShapedBoardVO> getBoardListEq(int start, int end, String category, String search);
	List<StairShapedBoardVO> getBoardListLk(int start, int end, String category, String search);
	List<StairShapedBoardVO> getBoardList(int start, int end);
	StairShapedBoardVO getBoard(int no);
	int increaseHit(int no);
	int countBoard();
	int countBoardEq(String category, String search);
	int countBoardLk(String category, String search);
	int insertBoard(StairShapedBoardVO board);
	int updateBoard(StairShapedBoardVO board);
	int deleteBoard(int no);
	BesideBoardVO besideBoard(int no);
}
