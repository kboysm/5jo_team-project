package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.bean.FileBoardVO;

@Mapper
public interface PageMakerMapper {

	public List<FileBoardVO> testlist(@Param("pagenum") int pagenum, 
			@Param("contentnum") int contentnum);
	
	public int testcount();
}
