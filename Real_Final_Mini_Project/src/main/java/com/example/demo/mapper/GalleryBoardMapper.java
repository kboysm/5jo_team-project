package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.bean.BoardFileVO;
import com.example.demo.bean.FileVO;
import com.example.demo.bean.GalleryBoardVo;

@Mapper
public interface GalleryBoardMapper {
	//일반게시판
	//게시글 개수
	int boardCount();
	//전체 리스트 출력
	List<GalleryBoardVo>selectAll();
	//게시글 상세
	GalleryBoardVo boardDetail(int no);
	//insert 구문
	int insertGalleryBoard(GalleryBoardVo galleryBoardvo);
	//update 구문
	int upadateGalleryBoard(GalleryBoardVo galleryBoardvo);
	//delete 구문
	int boardDelete(int no);

	//fileupload 및 insert
	int fileInsert(FileVO file);
	FileVO fileView(int no);
	
	//갤러리형 이미지 리스팅
	List <BoardFileVO> imagePrint();
	public List<BoardFileVO> imagePaging(@Param("pagenum")int pagenum, int contentnum); 
	//이미지 검색기능 만들기
	@Select("select count(*) from gallery_board B, gallery_files F where B.no = F.no and UPPER(title) like '%'|| UPPER(#{title}) ||'%' order by B.no desc")
	public int Ititle(@Param("title") String title);
	@Select("select count(*) from gallery_board B, gallery_files F where B.no = F.no and UPPER(writer) like '%'|| UPPER(#{writer}) ||'%' order by B.no desc")
	public int IWriter(@Param("writer") String writer);
	
	
	@Select("select * from (select rownum rnum, B.*from(select B.no,title,writer,filename,fileoriname,fileurl from gallery_board B, gallery_files F where B.no = F.no and UPPER(title) like '%'||UPPER(#{title})||'%' order by B.no desc)B)where rnum>=#{pagenum} and rnum <=#{contentnum}")
	public List<BoardFileVO> Xtitle(@Param("title") String title, @Param("pagenum")int pagenum,@Param("contentnum") int contentnum);
	@Select("select * from (select rownum rnum, B.*from(select B.no,title,writer,filename,fileoriname,fileurl from gallery_board B, gallery_files F where B.no = F.no and UPPER(writer) like '%'||UPPER(#{writer})||'%' order by B.no desc)B)where rnum>=#{pagenum} and rnum <=#{contentnum}")
	public List<BoardFileVO> Xwriter(@Param("writer") String writer, @Param("pagenum")int pagenum,@Param("contentnum") int contentnum);
	
	
	
	//방문시 조회수 증가
	int increaseCount(int no);
	
	//페이징 + 검색기능 위한 sql 구문
	@Select("select count(*) from gallery_Board where UPPER(title) like '%'|| UPPER(#{title}) ||'%'")
	public int Ctitle(@Param("title") String title);
	@Select("select count(*) from gallery_Board where UPPER(writer) like '%'|| UPPER(#{writer}) ||'%'")
	public int CWriter(@Param("writer") String writer);
	
	@Select("select * from (select rownum rnum, A.* from(select no,title,writer,content,regdate,count from gallery_board where UPPER(title) like '%'||UPPER(#{title})||'%' order by no desc)A) where rnum>=#{pagenum} and rnum <=#{contentnum}")
	public List<GalleryBoardVo> Stitle(@Param("title") String title, @Param("pagenum")int pagenum,@Param("contentnum") int contentnum);
	@Select("select * from (select rownum rnum, A.* from(select no,title,writer,content,regdate,count from gallery_board where UPPER(writer) like '%'||UPPER(#{writer})||'%' order by no desc)A) where rnum>=#{pagenum} and rnum <=#{contentnum}")
	public List<GalleryBoardVo> SWriter(@Param("writer") String writer, @Param("pagenum")int pagenum,@Param("contentnum") int contentnum);
}