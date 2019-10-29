package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StairShapedBoardService;
import com.example.demo.vo.PagingVO;

@RestController
public class StairShapedBoardJsonController {

	@Autowired
	StairShapedBoardService service;

	@GetMapping("stairShapedBoardSmallList2")
	public Map<String, Object> stairShapedBoardSmallList2(@RequestParam(defaultValue = "1") int smallNowPage,
			String grpno) {
		PagingVO vo = new PagingVO(smallNowPage, service.countBoardEq("grpno", grpno));
		vo.setCntPerPage(5);
		vo.pageSetting();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardList", service.getBoardListEq(vo.getStart(), vo.getEnd(), "grpno", grpno));
		map.put("paging", vo);
		return map;
	}
	
	

}
