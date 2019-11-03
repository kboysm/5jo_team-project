package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.bean.GalleryBoardVo;
@Mapper
public interface PagingMapper {
	public List<GalleryBoardVo> testlist(@Param("pagenum")int pagenum, int contentnum);
	public int boardCount();
	
	//GalleryBoardMapper.java 에서 실행하겠음
	//public List<BoardFileVO> imagePaging(@Param("pagenum")int pagenum, int contentnum);
}
