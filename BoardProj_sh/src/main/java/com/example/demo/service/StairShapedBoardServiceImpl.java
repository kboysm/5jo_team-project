package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.BesideBoardVO;
import com.example.demo.bean.StairShapedBoardVO;
import com.example.demo.mapper.StairShapedBoardMapper;

@Service("StairShapedBoardService")
public class StairShapedBoardServiceImpl implements StairShapedBoardService {
	@Autowired
	private StairShapedBoardMapper mapper;

	@Override
	public List<StairShapedBoardVO> getBoardListEq(int start, int end, String category, String search) {
		// TODO Auto-generated method stub
		return mapper.selectBoardList(start, end, category, search, "f");
	}

	@Override
	public List<StairShapedBoardVO> getBoardListLk(int start, int end, String category, String search) {
		// TODO Auto-generated method stub
		return mapper.selectBoardList(start, end, category, search, "t");
	}

	@Override
	public List<StairShapedBoardVO> getBoardList(int start, int end) {
		// TODO Auto-generated method stub
		return mapper.selectBoardList(start, end, null, null, null);
	}

	@Override
	public StairShapedBoardVO getBoard(int no) {
		// TODO Auto-generated method stub
		return mapper.selectBoard(no);
	}
	
	@Override
	public int increaseHit(int no) {
		// TODO Auto-generated method stub
		return mapper.increaseHit(no);
	}

	@Override
	public int countBoard() {
		// TODO Auto-generated method stub
		return mapper.countBoard(null, null, null);
	}

	@Override
	public int countBoardEq(String category, String search) {
		// TODO Auto-generated method stub
		return mapper.countBoard(category, search, "f");
	}

	@Override
	public int countBoardLk(String category, String search) {
		// TODO Auto-generated method stub
		return mapper.countBoard(category, search, "t");
	}

	@Override
	public int insertBoard(StairShapedBoardVO board) {
		// TODO Auto-generated method stub
		if (board.getPrntno() != 0)
			board.setGrpno(mapper.getPrntGrpno(board.getPrntno()));
		mapper.insertBoard(board);
		return mapper.getNoSeq();
	}

	@Override
	public int updateBoard(StairShapedBoardVO board) {
		// TODO Auto-generated method stub
		return mapper.updateBoard(board);
	}

	@Override
	public int deleteBoard(int no) {
		// TODO Auto-generated method stub
		return mapper.deleteBoard(no);
	}

	@Override
	public BesideBoardVO besideBoard(int no) {
		// TODO Auto-generated method stub
		return mapper.besideBoard(no);
	}


}
