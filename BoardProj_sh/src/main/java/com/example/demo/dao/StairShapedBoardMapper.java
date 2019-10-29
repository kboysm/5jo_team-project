package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.BesideBoardVO;
import com.example.demo.vo.StairShapedBoardVO;

@Mapper
public interface StairShapedBoardMapper {
	/*
	 * @Select("SELECT * FROM (SELECT ROWNUM rnum, ssb.* FROM ( " +
	 * "SELECT LEVEL, no, writer, title, regdate, hits FROM stair_shaped_board " +
	 * "WHERE UPPER(${category}) LIKE '%'||UPPER(#{search})||'%' " +
	 * "START WITH prntno IS NULL CONNECT BY PRIOR no=prntno " +
	 * "ORDER SIBLINGS BY grpno desc " +
	 * ") ssb) WHERE rnum >= #{start} AND rnum<=#{end}")
	 */
	// List<StairShapedBoardVO> selectBoardListBy(int start, int end, String
	// category, String search);


	/*
	 * @Select("SELECT * FROM (SELECT ROWNUM rnum, ssb.* FROM ( " +
	 * "SELECT LEVEL, no, writer, title, regdate, hits FROM stair_shaped_board " +
	 * "START WITH prntno IS NULL CONNECT BY PRIOR no=prntno ORDER SIBLINGS BY grpno desc "
	 * + ") ssb) WHERE rnum >= #{start} AND rnum<=#{end}")
	 */
	// List<StairShapedBoardVO> selectBoardList(int start, int end, String where);

	// @Select("SELECT no, grpno, prntno, writer, title, content, regdate, hits FROM
	// stair_shaped_board WHERE no=#{no}")

	// @Select("SELECT COUNT(no) FROM stair_shaped_board")
	// int countBoard();

	// @Select("SELECT COUNT(no) FROM stair_shaped_board WHERE UPPER(${category})
	// LIKE UPPER('%'||#{search}||'%')")
	// int countBoardBy(String category, String search);

	/*
	 * @Insert("INSERT INTO stair_shaped_board(no, grpno, prntno, title, writer, content) VALUES( "
	 * +
	 * "stair_shaped_board_seq.NEXTVAL, ${group}, #{prntno}, #{title}, #{writer}, #{content})"
	 * )
	 */

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
