package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.BesideBoardVO;
import com.example.demo.bean.StairShapedBoardVO;

@Mapper
public interface StairShapedBoardMapper {
	
	List<StairShapedBoardVO> selectBoardList(int start, int end, String category, String search, String like);
	StairShapedBoardVO selectBoard(int no);
	int countBoard(String category, String search, String like);
	int getPrntGrpno(int no);
	int insertBoard(StairShapedBoardVO board);
	int getNoSeq();
	int increaseHit(int no);
	int updateBoard(StairShapedBoardVO board);
	int deleteBoard(int no);
	BesideBoardVO besideBoard(int no);
}
