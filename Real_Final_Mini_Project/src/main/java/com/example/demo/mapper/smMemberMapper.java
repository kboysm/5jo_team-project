package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.bean.smBoardVO;
@Mapper
public interface smMemberMapper {
	@Select("select * from List_Board where idx = #{idx}")
	public smBoardVO smselectIdx(@Param("idx") int idx);
	
	public void sminsertBoard (smBoardVO board);
    public void smupdateBoard (smBoardVO board);
    public void smdeleteBoard (int idx);
    public smBoardVO smselectBoard (int idx); 
    
    @Select("SELECT * FROM ( SELECT p.*, ROW_NUMBER() OVER(ORDER BY idx DESC) AS RNUM FROM List_board p) WHERE RNUM BETWEEN #{pagenum} AND #{contentnum} ")
    public List<smBoardVO> plist(@Param("pagenum") int pagenum,@Param("contentnum") int contentnum);
  //페이지 번호를 가져오고(현재 페이지 번호) ,몇개를 가져오는지(한 페이지에 몇개를 표시할 지)
    @Select("select count(*) from List_board")
    public int pcount();
  //전체 게시글의 갯수를 가져오는 함수이다.
    
    ////////////////
    @Select("select count(*) from List_Board where title like '%'|| #{title} ||'%'")
    public int CStitle(@Param("title") String title);
    
    @Select("select count(*) from List_Board where writer like '%'||#{writer}||'%'")
    public int CWtitle(@Param("writer") String writer);
    ////페이징 처리를 위한 count함수들

    
    ////////////////////////////////////////////////////////////////////////////////////////////  
    @Select("SELECT * FROM ( SELECT p.*, ROW_NUMBER() OVER(ORDER BY idx DESC) AS RNUM FROM List_board p where title like '%'||#{title}||'%') WHERE RNUM BETWEEN #{pagenum} AND #{contentnum}")
    public List<smBoardVO> Stitle(@Param("title") String title,@Param("pagenum") int pagenum,@Param("contentnum") int contentnum);
    //페이징 + 검색기능을 위한 sql구문
    @Select("SELECT * FROM ( SELECT p.*, ROW_NUMBER() OVER(ORDER BY idx DESC) AS RNUM FROM List_board p where writer like '%'||#{writer}||'%') WHERE RNUM BETWEEN #{pagenum} AND #{contentnum}")
    public List<smBoardVO> Wtitle(@Param("writer") String writer,@Param("pagenum") int pagenum,@Param("contentnum") int contentnum);
    ////////////////////////////////////////////////////////////////////////////////////////////  
    
   
    
}
