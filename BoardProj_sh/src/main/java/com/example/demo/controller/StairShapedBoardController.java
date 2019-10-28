package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.StairShapedBoardService;
import com.example.demo.vo.PagingVO;
import com.example.demo.vo.StairShapedBoardVO;

@Controller
public class StairShapedBoardController {

	@Autowired
	StairShapedBoardService service;

	@GetMapping("index")
	public void index() {
	}

	@GetMapping("stairShapedBoardList")
	public void stairShapedBoardList(@RequestParam(defaultValue = "1") int nowPage, String category, String search,	Model model) {
		PagingVO vo;
		if (category != null) {
			vo = new PagingVO(nowPage, service.countBoardLk(category, search));
			model.addAttribute("boardList", service.getBoardListLk(vo.getStart(), vo.getEnd(), category, search));
			model.addAttribute("searching", "searching");
		} else {
			vo = new PagingVO(nowPage, service.countBoard());
			model.addAttribute("boardList", service.getBoardList(vo.getStart(), vo.getEnd()));
		}
		System.out.println(vo.getStartPage());
		System.out.println(vo.getTotalPage());
		System.out.println(vo.getEndPage());
		model.addAttribute("paging", vo);
		model.addAttribute("nowPage",nowPage);
		model.addAttribute("category",category);
		model.addAttribute("search",search);
	}

	
	@RequestMapping("stairShapedBoard")
	public void stairShapedBoard(@RequestParam(defaultValue = "1") int smallNowPage, @RequestParam(defaultValue = "1") int nowPage, String category, String search, int no, Model model) {
		StairShapedBoardVO board = service.getBoard(no);
		String grpno=Integer.toString(board.getGrpno());
		model.addAttribute("thisBoard", board);
		PagingVO vo = new PagingVO(smallNowPage, service.countBoardEq("grpno",grpno));
		model.addAttribute("boardList", service.getBoardListEq(vo.getStart(), vo.getEnd(), "grpno", grpno));
		model.addAttribute("paging", vo);
		model.addAttribute("nowPage",nowPage);
		model.addAttribute("category",category);
		model.addAttribute("search",search);
	}

	@GetMapping("stairShapedBoardInsertForm")
	public void stairShapedBoardInsertForm(@ModelAttribute StairShapedBoardVO board) {

	}

	@PostMapping("stairShapedBoardInsert")
	public String stairShapedBoardInsert(@ModelAttribute StairShapedBoardVO board, Model model) {
		int no=service.insertBoard(board);
		System.out.println(no);
		return "forward:stairShapedBoard?no="+no;
	}

}
