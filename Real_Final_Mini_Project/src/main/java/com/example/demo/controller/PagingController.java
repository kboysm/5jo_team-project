package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bean.GalleryBoardVo;
import com.example.demo.bean.PageMaker;
import com.example.demo.mapper.GalleryBoardMapper;
import com.example.demo.mapper.PagingMapper;
import com.example.demo.service.GalleryBoardService;

@Controller
public class PagingController {

	 @Autowired
	 PagingMapper mapper;
	 @Autowired
	 GalleryBoardMapper galleryBoardMapper;
	 @Autowired
	 GalleryBoardService galleryBoardService;
	 
	 @GetMapping("test")
	 public String showTestPage(Model model) {
		 model.addAttribute("boardlist", galleryBoardService.selectAll());
		 return "test";
	 }
	 
	 @RequestMapping("galleryBoardList3")
	 	public String galleyBoardList3(HttpServletRequest request,Model model) {
		 
		 String testnum = request.getParameter("pagenum");
		 if(testnum==null) {
			 testnum="1";
		 }
		 String tcontentnum = request.getParameter("contentnum");
		 if(tcontentnum == null) {
			 tcontentnum ="10";
		 }
		 
		 int contentnum = Integer.parseInt(tcontentnum);
		 int pagenum = Integer.parseInt(testnum);
		 PageMaker pageMaker = new PageMaker();
		 pageMaker.setTotalcount(mapper.boardCount()); //전체 게시글 개수 지정
		 pageMaker.setPagenum(pagenum);			   //현재 페이지를 페이지 객체에 지정한다. -1을 해야 쿼리에서 사용가능	
		 //
		 pageMaker.setContentnum(contentnum);		//한 페이지에 몇개씩 게시글을 보여줄지	
		 pageMaker.setCurrentblock(pagenum);		//현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해 지정
		 pageMaker.setLastblock(pageMaker.getTotalcount()); //마지막으로 블록 번호를  전체 게시글 수로 정한다.
		 
		 pageMaker.prevnext(pagenum); //현재 페이지 번호로 화살표를 나타낼지 정한다.
		 pageMaker.setStartPage(pageMaker.getCurrentblock()); //시작 페이지를 페이지 블록 번호로 정한다.
		 pageMaker.setEndPage(pageMaker.getLastblock(), pageMaker.getCurrentblock()); //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
		 
		 List<GalleryBoardVo> testlist = new ArrayList<>();
		 testlist = mapper.testlist(pageMaker.getPagenum()*10-9, pageMaker.getPagenum()*10);
		
		 model.addAttribute("total", "총"+galleryBoardService.boardCount()+"개의 글");
		 model.addAttribute("boardlist", testlist);
		 model.addAttribute("page", pageMaker);
		 //model.addAttribute("imageData", galleryBoardMapper.fileView(no).getFileUrl()+galleryBoardMapper.fileView(no).getFileName());
		 return "galleryBoardList3";
	 }
	 //페이징(외국인 깃 참조 잘 안되서 일단 pass)
	 /*
	  * 
	 public PagingController(PagingService testService) {
		 this.pagingService = testService;
	 }
	
	 private static final int BUTTONS_TO_SHOW = 5;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = {5, 10, 20};
	 
	 private final PagingService pagingService;
	 
	 @GetMapping("test")
	 public ModelAndView showTestPage(Model model, @RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page) {
		 ModelAndView modelAndView = new ModelAndView("boardlist");
		 
		 int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		 int evalPage = (page.orElse(0)<1)?INITIAL_PAGE :page.get() -1;
		
		 Page<GalleryBoardVo> boardlist = pagingService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		 Pager pager = new Pager(boardlist.getTotalPages(),boardlist.getNumber(),BUTTONS_TO_SHOW);
		 
		 modelAndView.addObject("boardlist", boardlist);
		 modelAndView.addObject("selectedPageSize", evalPageSize);
	     modelAndView.addObject("pageSizes", PAGE_SIZES);
	     modelAndView.addObject("pager", pager);
		 return modelAndView;

		 
		
		 return modelAndView;
	 }
	  */
}
