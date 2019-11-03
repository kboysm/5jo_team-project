package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.bean.ListBoardVO;
import com.example.demo.service.ListBoardService;

@Controller
@RequestMapping("/yjBoard")
public class ListBoardController {

	@Autowired
	ListBoardService service;
	
	//게시물 작성 화면 호출
	@GetMapping("/writeContent")
	public void write(@ModelAttribute("vo") ListBoardVO vo) {
		
	}
	//게시물 작성
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public String insert(Model model, ListBoardVO vo)  {
		service.insert(vo);
		return "redirect:/yjBoard/list";
	}
	//조회
	@GetMapping("/viewContent")
	public String viewContent(Model model,@RequestParam(value="idx",required = false) Integer idx) throws Exception{
		model.addAttribute("viewContent",service.viewContent(idx));
		return "/yjBoard/viewContent";
		
	}
	//게시물 수정 호출
	@GetMapping("/updateContent")
	public String updateGet(Model model,@RequestParam(value="idx",required = false) Integer idx) throws Exception{
		model.addAttribute("viewContent",service.viewContent(idx));
		return "/yjBoard/update";
	}
	//게시물 수정
	@RequestMapping(value="/update")
	public String updatePost(ListBoardVO vo, Model model) throws Exception {
		System.out.println(vo);
		service.update(vo);
		
		return "redirect:/yjBoard/list";
	}
	
	//게시물 삭제 
	@GetMapping("/delete")
	public String delete(@RequestParam(value="idx",required = false) Integer idx) throws Exception {
		service.delete(idx);
		
		return "redirect:/yjBoard/list";
	}
	
	
	//게시물 목록 호출
	@RequestMapping("/list")
	public String viewList(Model model) {
	model.addAttribute("list", service.viewList());
	
	return "/yjBoard/list";
	}
	
	
	//게시물 검색 
	@RequestMapping("search")
	public String search(Model model,String searchType,String search) throws Exception {
		List<ListBoardVO> list = new ArrayList<ListBoardVO>();
		
		
		if(searchType.equals("title")) {
			System.out.println(service.findByTitle(search));
			model.addAttribute("list",service.findByTitle(search));
		} else {
			model.addAttribute("list", service.findByWriter(search));
		}
		return "/yjBoard/list";
	}
	
}
