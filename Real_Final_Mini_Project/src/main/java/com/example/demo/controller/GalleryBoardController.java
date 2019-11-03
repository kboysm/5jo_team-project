package com.example.demo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.bean.BoardFileVO;
import com.example.demo.bean.FileVO;
import com.example.demo.bean.GalleryBoardVo;
import com.example.demo.bean.PageMaker;
import com.example.demo.mapper.GalleryBoardMapper;
import com.example.demo.mapper.PagingMapper;
import com.example.demo.service.GalleryBoardService;
import com.example.demo.service.GalleryBoardServiceImpl;

@Controller
public class GalleryBoardController {
	private static final Logger logger = LoggerFactory.getLogger(GalleryBoardServiceImpl.class);

	@Autowired
	GalleryBoardMapper galleryBoardMapper;
	@Autowired
	GalleryBoardService galleryBoardService;
	@Autowired
	PagingMapper mapper;
	
	
	//현재 저장된 gallery_board 전체 출력
	@RequestMapping("test")
	public String test(Model model, GalleryBoardVo vo) {
		model.addAttribute("test", galleryBoardService.selectAll());
		return "test";
	}
	//gallery_board 입력(insert)
	@RequestMapping("galleryBoardEditor")
	public void galleryBoardEditor(Model model, @ModelAttribute GalleryBoardVo galleryBoardVo) {
	}
	//일단 insert가 되고있다. 
	
	@PostMapping("galleryBoardInsert")
	public String galleryBoardInsert(HttpServletRequest request, @RequestPart("files") MultipartFile files) throws Exception {
		GalleryBoardVo galleryBoardVo = new GalleryBoardVo();
		FileVO file = new FileVO();
		
		galleryBoardVo.setTitle(request.getParameter("title"));
		galleryBoardVo.setContent(request.getParameter("content"));
		galleryBoardVo.setWriter(request.getParameter("writer"));

		if(files.isEmpty()) {
			galleryBoardMapper.insertGalleryBoard(galleryBoardVo);
	
		}else {
			String fileName = files.getOriginalFilename();
			String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase(); 
			File destinationFile; 
			String destinationFileName;
			String fileUrl = "/uploadFiles/";

	        do { 
	        	destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
	            destinationFile = new File(request.getServletContext().getRealPath(fileUrl) + destinationFileName); 
	       
	            System.out.println(destinationFile);
	        } while (destinationFile.exists()); 
	        
	        destinationFile.getParentFile().mkdirs(); 
			files.transferTo(destinationFile);
	        
			//게시글 insert
	        galleryBoardMapper.insertGalleryBoard(galleryBoardVo);

	        file.setNo(galleryBoardVo.getNo());
	        file.setFileName(destinationFileName);
	        file.setFileOriName(fileName);
	        file.setFileUrl(fileUrl);
	        
	        System.out.println("file 생성되나 Test => " + file);
	        
	        galleryBoardMapper.fileInsert(file);
		}
	        
		return "redirect:/galleryBoardList";
	}
	
	/*
	@PostMapping("galleryBoardInsert")
	public String galleryBoardInsert(@ModelAttribute GalleryBoardVo galleryBoardVo,Model model) {
		int no = galleryBoardService.insertGalleryBoard(galleryBoardVo);
		System.out.println(no);
		//insert 후 출력(총게시글 수/목록)
		model.addAttribute("total", "총"+galleryBoardService.boardCount()+"개의 글");
		model.addAttribute("boardlist", galleryBoardService.selectAll());
		
		return "galleryBoardList";
	}
	*/
	/*
	@RequestMapping("galleryBoardList")
	public String galleryBoardList(Model model) {
		model.addAttribute("total", "총"+galleryBoardService.boardCount()+"개의 글");
		model.addAttribute("boardlist", galleryBoardService.selectAll());
		return "galleryBoardList";
	}
	*/
	//detail한 화면을 보고싶다.
	@RequestMapping("galleryBoardDetail")
	public void galleryBoardDetail(int no, Model model ) {
		GalleryBoardVo galleryBoardVo = galleryBoardService.boardDetail(no);
		
		model.addAttribute("imageData", galleryBoardMapper.fileView(no).getFileUrl()+galleryBoardMapper.fileView(no).getFileName());
		model.addAttribute("thisBoard", galleryBoardVo);
		
	}
	
	//update()
	@RequestMapping("updateProc")
	public String galleryBoardupdateProc(HttpServletRequest request) {
		GalleryBoardVo galleryBoardVo = new GalleryBoardVo();
		
		galleryBoardVo.setTitle(request.getParameter("title"));
		galleryBoardVo.setContent(request.getParameter("content"));
		galleryBoardVo.setNo(Integer.parseInt(request.getParameter("no")));
		
		galleryBoardService.upadateGalleryBoard(galleryBoardVo);
		
		
		return "redirect:/galleryBoardDetail?no="+request.getParameter("no");
	}
	@RequestMapping("galleryBoardUpdate")
	public void galleryBoardUpdate(Model model, int no) {
		model.addAttribute("galleryBoardDetail", galleryBoardService.boardDetail(no));
	}
	
	@RequestMapping("galleryBoardDelete")
	public String galleryBoardDelete(HttpServletRequest request,Model model, int no) {
		galleryBoardService.boardDelete(no);
		//model.addAttribute("total", "총"+galleryBoardService.boardCount()+"개의 글");
		//model.addAttribute("boardlist", galleryBoardService.selectAll());
		String testnum = request.getParameter("pagenum");
		 if(testnum==null) {
			 testnum="1";
		 }
		 int pagenum = Integer.parseInt(testnum);
		 PageMaker pageMaker = new PageMaker();
		 pageMaker.setTotalcount(mapper.boardCount()); //전체 게시글 개수 지정
		 pageMaker.setPagenum(pagenum);			   //현재 페이지를 페이지 객체에 지정한다. -1을 해야 쿼리에서 사용가능	
		 pageMaker.setContentnum(pagenum);		//한 페이지에 몇개씩 게시글을 보여줄지	
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
		return "galleryBoardList";
	}
	/*
	@RequestMapping("galleryBoardImage")
	public String galleryBoardImage(Model model,HttpServletRequest request) {
		//List <BoardFileVO> vo = galleryBoardService.imagePrint();
		//GalleryBoardVo galleryBoardVo = galleryBoardService.boardDetail(no);
		
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
		 pageMaker.setTotalcount(galleryBoardMapper.boardCount()); //전체 게시글 개수 지정
		 pageMaker.setPagenum(pagenum);			   //현재 페이지를 페이지 객체에 지정한다. -1을 해야 쿼리에서 사용가능	
		 pageMaker.setContentnum(contentnum);		//한 페이지에 몇개씩 게시글을 보여줄지	
		 pageMaker.setCurrentblock(pagenum);		//현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해 지정
		 pageMaker.setLastblock(pageMaker.getTotalcount()); //마지막으로 블록 번호를  전체 게시글 수로 정한다.
		 
		 pageMaker.prevnext(pagenum); //현재 페이지 번호로 화살표를 나타낼지 정한다.
		 pageMaker.setStartPage(pageMaker.getCurrentblock()); //시작 페이지를 페이지 블록 번호로 정한다.
		 pageMaker.setEndPage(pageMaker.getLastblock(), pageMaker.getCurrentblock()); //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
		 
		 List<BoardFileVO> testlist = new ArrayList<>();
		 testlist = galleryBoardMapper.imagePaging(pageMaker.getPagenum()*10-9, pageMaker.getPagenum()*10);
		 
		 model.addAttribute("total", "총"+galleryBoardService.boardCount()+"개의 글");
		 model.addAttribute("imagetest", testlist);
		 model.addAttribute("page", pageMaker);
		
		return "galleryBoardImage";
	}
	*/
		@GetMapping("search")
		public String search(HttpServletRequest request,Model model,String option,String search) {
			String testnum = request.getParameter("pagenum");
			if(testnum==null) {
				testnum="1";
			}
			int pagenum = Integer.parseInt(testnum);
			
			 int contentnum = 10;
			
			PageMaker pageMaker = new PageMaker();
			List<GalleryBoardVo> testlist = new ArrayList<>();
			
			//testlist = mapper.testlist(pageMaker.getPagenum()*10-9, pageMaker.getPagenum()*10);
			if(option.equals("글쓴이")) {
				 pageMaker.setTotalcount(galleryBoardMapper.CWriter(search)); 
				 pageMaker.setPagenum(pagenum);			  
				 pageMaker.setCurrentblock(pagenum);		
				 pageMaker.setLastblock(pageMaker.getTotalcount());
				 pageMaker.setTotalpage(pageMaker.getTotalcount(), contentnum);
				 
				 pageMaker.prevnext(pagenum); 
				 pageMaker.setStartPage(pageMaker.getCurrentblock()); 
				 pageMaker.setEndPage(pageMaker.getLastblock(), pageMaker.getCurrentblock()); 
			
				 testlist = galleryBoardMapper.SWriter(search, (pageMaker.getPagenum()*10)-9, pageMaker.getPagenum()*10);
			
				 model.addAttribute("boardlist", testlist);
				 model.addAttribute("page", pageMaker);
				 model.addAttribute("key", option);
				 model.addAttribute("search", search);
			}else {
				 pageMaker.setTotalcount(galleryBoardMapper.Ctitle(search)); 
				 pageMaker.setPagenum(pagenum);			  
				 pageMaker.setCurrentblock(pagenum);		
				 pageMaker.setLastblock(pageMaker.getTotalcount());
				 pageMaker.setTotalpage(pageMaker.getTotalcount(), contentnum);
				 
				 pageMaker.prevnext(pagenum); 
				 pageMaker.setStartPage(pageMaker.getCurrentblock()); 
				 pageMaker.setEndPage(pageMaker.getLastblock(), pageMaker.getCurrentblock()); 
			
				 testlist = galleryBoardMapper.SWriter(search, (pageMaker.getPagenum()*10)-9, pageMaker.getPagenum()*10);
			
				 model.addAttribute("boardlist", testlist);
				 model.addAttribute("page", pageMaker);
				 model.addAttribute("key", option);
				 model.addAttribute("search", search);
			}
			model.addAttribute("total", "총"+galleryBoardService.boardCount()+"개의 글");
			
			return "galleryBoardList";
		}
		
		@GetMapping("galleryBoardList")
		public String galleryBoardList(HttpServletRequest request,Model model ,String key,String search) {
			String testnum = request.getParameter("pagenum");
			if(testnum==null) {
				testnum="1";
			}
			int pagenum = Integer.parseInt(testnum);

			//이거
			 int contentnum = 10;
			
			
			PageMaker pageMaker = new PageMaker();
			List<GalleryBoardVo> testlist = null;
			pageMaker.setPagenum(pagenum);			   //현재 페이지를 페이지 객체에 지정한다. -1을 해야 쿼리에서 사용가능	
			if(key==null) {
				//System.out.println("null이라구");
				 pageMaker.setTotalcount(mapper.boardCount()); //전체 게시글 개수 지정
				 //List<GalleryBoardVo> testlist = new ArrayList<>();
				 testlist = mapper.testlist(pageMaker.getPagenum()*10-9, pageMaker.getPagenum()*10);
			}
			if(key!=null) {
				if(key.equals("글쓴이")) {
					pageMaker.setTotalcount(galleryBoardMapper.CWriter(search)); 
					
					testlist = galleryBoardMapper.SWriter(search, (pageMaker.getPagenum()*10)-9, pageMaker.getPagenum()*10);
					
					model.addAttribute("key", key);
					model.addAttribute("search", search);
				}else {
					pageMaker.setTotalcount(galleryBoardMapper.Ctitle(search)); 
					
					testlist = galleryBoardMapper.SWriter(search, (pageMaker.getPagenum()*10)-9, pageMaker.getPagenum()*10);
					
					model.addAttribute("key", key);
					model.addAttribute("search", search);
				}
			}
			 pageMaker.setContentnum(contentnum);		
			 pageMaker.setCurrentblock(pagenum);		
			 pageMaker.setLastblock(pageMaker.getTotalcount()); 
			 //이거
			 pageMaker.setTotalpage(pageMaker.getTotalcount(), contentnum);
			 
			 pageMaker.prevnext(pagenum); 
			 pageMaker.setStartPage(pageMaker.getCurrentblock()); 
			 pageMaker.setEndPage(pageMaker.getLastblock(), pageMaker.getCurrentblock()); 
			 model.addAttribute("boardlist", testlist);
			 model.addAttribute("page", pageMaker);
			 model.addAttribute("total", "총"+galleryBoardService.boardCount()+"개의 글");
			return "galleryBoardList";
		}
		//Imgae test
		@GetMapping("searchImg")
		public String searchImg(HttpServletRequest request,Model model,String option,String search) {
			String testnum = request.getParameter("pagenum");
			if(testnum==null) {
				testnum="1";
			}
			int pagenum = Integer.parseInt(testnum);
			int contentnum = 10;
			
			
			PageMaker pageMaker = new PageMaker();
			List<BoardFileVO> testlist = new ArrayList<>();
			
			//testlist = mapper.testlist(pageMaker.getPagenum()*10-9, pageMaker.getPagenum()*10);
			if(option.equals("글쓴이")) {
				pageMaker.setTotalcount(galleryBoardMapper.IWriter(search)); 
				pageMaker.setPagenum(pagenum);			  
				pageMaker.setCurrentblock(pagenum);		
				pageMaker.setLastblock(pageMaker.getTotalcount());
				pageMaker.setTotalpage(pageMaker.getTotalcount(), contentnum);
				
				pageMaker.prevnext(pagenum); 
				pageMaker.setStartPage(pageMaker.getCurrentblock()); 
				pageMaker.setEndPage(pageMaker.getLastblock(), pageMaker.getCurrentblock()); 
				
				testlist = galleryBoardMapper.Xwriter(search, (pageMaker.getPagenum()*10)-9, pageMaker.getPagenum()*10);
				
				model.addAttribute("imagetest", testlist);
				model.addAttribute("page", pageMaker);
				model.addAttribute("key", option);
				model.addAttribute("search", search);
			}else {
				pageMaker.setTotalcount(galleryBoardMapper.Ititle(search)); 
				pageMaker.setPagenum(pagenum);			  
				pageMaker.setCurrentblock(pagenum);		
				pageMaker.setLastblock(pageMaker.getTotalcount());
				pageMaker.setTotalpage(pageMaker.getTotalcount(), contentnum);
				
				pageMaker.prevnext(pagenum); 
				pageMaker.setStartPage(pageMaker.getCurrentblock()); 
				pageMaker.setEndPage(pageMaker.getLastblock(), pageMaker.getCurrentblock()); 
				
				testlist = galleryBoardMapper.Xtitle(search, (pageMaker.getPagenum()*10)-9, pageMaker.getPagenum()*10);
				
				model.addAttribute("imagetest", testlist);
				model.addAttribute("page", pageMaker);
				model.addAttribute("key", option);
				model.addAttribute("search", search);
			}
			model.addAttribute("total", "총"+galleryBoardService.boardCount()+"개의 글");
			
			return "galleryBoardImage";
		}
		
		@GetMapping("galleryBoardImage")
		public String test2(HttpServletRequest request,Model model ,String key,String search) {
			String testnum = request.getParameter("pagenum");
			if(testnum==null) {
				testnum="1";
			}
			int pagenum = Integer.parseInt(testnum);
			int contentnum = 10;
			
			
			PageMaker pageMaker = new PageMaker();
			List<BoardFileVO> testlist = null;
			pageMaker.setPagenum(pagenum);			   //현재 페이지를 페이지 객체에 지정한다. -1을 해야 쿼리에서 사용가능	
			if(key==null) {
				//System.out.println("null이라구");
				pageMaker.setTotalcount(mapper.boardCount()); //전체 게시글 개수 지정
				//List<GalleryBoardVo> testlist = new ArrayList<>();
				testlist = galleryBoardMapper.imagePaging(pageMaker.getPagenum()*10-9, pageMaker.getPagenum()*10);
			}
			if(key!=null) {
				if(key.equals("글쓴이")) {
					pageMaker.setTotalcount(galleryBoardMapper.CWriter(search)); 
					
					testlist = galleryBoardMapper.Xtitle(search, (pageMaker.getPagenum()*10)-9, pageMaker.getPagenum()*10);

					model.addAttribute("key", key);
					model.addAttribute("search", search);
				}else {
					pageMaker.setTotalcount(galleryBoardMapper.Ctitle(search)); 
					testlist = galleryBoardMapper.Xwriter(search, (pageMaker.getPagenum()*10)-9, pageMaker.getPagenum()*10);
					
					model.addAttribute("key", key);
					model.addAttribute("search", search);
				}
			}
			pageMaker.setContentnum(pagenum);		
			pageMaker.setCurrentblock(pagenum);		
			pageMaker.setLastblock(pageMaker.getTotalcount()); 
			//이거
			pageMaker.setTotalpage(pageMaker.getTotalcount(), contentnum);
			 
			pageMaker.prevnext(pagenum); 
			pageMaker.setStartPage(pageMaker.getCurrentblock()); 
			pageMaker.setEndPage(pageMaker.getLastblock(), pageMaker.getCurrentblock()); 
			model.addAttribute("imagetest", testlist);
			model.addAttribute("page", pageMaker);
			model.addAttribute("total", "총"+galleryBoardService.boardCount()+"개의 글");
			return "galleryBoardImage";
		}
}
