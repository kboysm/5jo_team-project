package com.example.demo.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		model.addAttribute("paging", vo);
		model.addAttribute("nowPage",nowPage);
		model.addAttribute("category",category);
		model.addAttribute("search",search);
	}
	
	
	@RequestMapping("stairShapedBoard")
	public void stairShapedBoard(@RequestParam(defaultValue = "1") int nowPage, @RequestParam(defaultValue = "1") int smallNowPage, String category, String search, int no, Model model) {
		StairShapedBoardVO board = service.getBoard(no);
		String grpno=Integer.toString(board.getGrpno());
		PagingVO vo = new PagingVO(smallNowPage, service.countBoardEq("grpno", grpno));
		vo.setCntPerPage(5);
		vo.pageSetting();
		model.addAttribute("boardList", service.getBoardListEq(vo.getStart(), vo.getEnd(), "grpno", grpno));
		model.addAttribute("paging", vo);
		model.addAttribute("grpno",board.getGrpno());
		model.addAttribute("thisBoard", board);
		model.addAttribute("nowPage",nowPage);
		model.addAttribute("smallNowPage",smallNowPage);
		model.addAttribute("category",category);
		model.addAttribute("search",search);
		model.addAttribute("beside",service.besideBoard(no));
		model.addAttribute("total",service.countBoard());
	}

	@GetMapping("stairShapedBoardInsertForm")
	public void stairShapedBoardInsertForm(@ModelAttribute("board") StairShapedBoardVO board, @RequestParam(defaultValue = "0") int prntno) {
		board.setPrntno(prntno);
	}

	@PostMapping("stairShapedBoardInsert")
	public String stairShapedBoardInsert(@ModelAttribute("board") StairShapedBoardVO board, Model model) {
		int no=service.insertBoard(board);
		return "redirect:stairShapedBoard?no="+no;
	}
	
	@GetMapping("stairShapedBoardUpdateForm")
	public void stairShapedBoardUpdateForm(int no, Model model) {
		model.addAttribute("board",service.getBoard(no));
	}
	
	@PostMapping("stairShapedBoardUpdate")
	public String stairShapedBoardUpdate(@ModelAttribute("board") StairShapedBoardVO board, Model model) {
		service.updateBoard(board);
		return "redirect:stairShapedBoard?no="+board.getNo();
	}
	@GetMapping("stairShapedBoardDelete")
	public String stairShapedBoardDelete(int no, Model model) {
		service.deleteBoard(no);
		return "redirect:stairShapedBoardList";
	}
	
	@GetMapping("stairShapedBoardSmallList")
	public String stairShapedBoardSmallList(@RequestParam(defaultValue = "1") int nowPage, @RequestParam(defaultValue = "1") int smallNowPage,
			String grpno, int no, Model model) {
		PagingVO vo = new PagingVO(smallNowPage, service.countBoardEq("grpno", grpno));
		vo.setCntPerPage(5);
		vo.pageSetting();
		model.addAttribute("nowPage",nowPage);
		model.addAttribute("no",no);
		model.addAttribute("boardList", service.getBoardListEq(vo.getStart(), vo.getEnd(), "grpno", grpno));
		model.addAttribute("paging", vo);
		return "stairShapedBoardListFrag :: boardListTable";
	}

}
