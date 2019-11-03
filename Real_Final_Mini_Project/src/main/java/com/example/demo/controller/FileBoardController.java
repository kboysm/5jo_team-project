package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.bean.FileBoardVO;
import com.example.demo.bean.PageMakerVO;
import com.example.demo.bean.UpdateFileVO;
import com.example.demo.mapper.PageMakerMapper;
import com.example.demo.service.FileBoardService;

@Controller
@RequestMapping("/fileBoard")
public class FileBoardController {
/*
	@Autowired
	FileBoardMapper fboardMapper;
*/	
	@Autowired
	PageMakerMapper pageMakerMapper;
	
	@Autowired
	FileBoardService fboardService;
	
	@RequestMapping("/list")
	private String fileBoardList(Model model, HttpServletRequest request) {

		//페이징처리 설정 시작
		
		//현재 페이지 초기화 작업
		String testPageNum = request.getParameter("pagenum");
		if(testPageNum == null) {
			testPageNum = "1" ;
		}
		int pagenum = Integer.parseInt(testPageNum);
/*		
		String testContentNum = request.getParameter("contentnum"); //한 페이지에 몇 개 표시할지
		if(testContentNum == null) {
			testContentNum = "10";
		}
		int contentnum = Integer.parseInt(testContentNum);
*/		
		int contentnum = 10;
		
		//int cpagenum = Integer.parseInt(pagenum); //integer 변환 이건 위에서 작업 했음
		//int ccontentnum = Integer.parseInt(contentnum); //integer 변환도 위에서 작업함

		PageMakerVO pagemaker = new PageMakerVO();
		//System.out.println(pagenum);
		//System.out.println(contentnum);
		
		pagemaker.setTotalcount(pageMakerMapper.testcount()); //게시글 전체 갯수 지정
			System.out.println("게시글 전체 갯수 testcount() : "+pagemaker.getTotalcount());
		pagemaker.setPagenum(pagenum); //현재 페이지를 페이지 객체에 지정. -1을 해야 쿼리에서 사용 가능
			System.out.println("현재 페이지 번호 getPagenum() : "+pagemaker.getPagenum());
		pagemaker.setContentnum(contentnum); //한 페이지에 개시글을 몇 개 보여줄지 지정.
			System.out.println("한 페이지에 게시물 몇 개 표시할지 getContentnum() : "+pagemaker.getContentnum());
		pagemaker.setCurrentblock(pagenum); // 현재 페이지 블록이 몇 번 인지 현재 페이지 번호를 통해 지정
			System.out.println("현재 페이지 블럭 getCurrentblock : "+pagemaker.getCurrentblock());
		pagemaker.setLastblock(pagemaker.getTotalcount()); //마지막 블록 번호를 전체 게시글 수를 통해 지정
			System.out.println("마지막 페이지 블럭 getLastblock : "+pagemaker.getLastblock());
		pagemaker.setTotalPage(pagemaker.getTotalcount(), contentnum);
		pagemaker.prevnext(pagenum); //현재 페이지 번호로 화살표를 나타낼지 정함
		pagemaker.setStartPage(pagemaker.getCurrentblock()); //시작 페이지를 페이지 블록 번호로 정한다
			System.out.println("현재 페이지블럭의 시작 페이지 getCurrentblock() : "+pagemaker.getCurrentblock());
		pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
			System.out.println("현재 페이지블럭의 마지막 페이지 getEndPage() : "+pagemaker.getEndPage());
		//마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다

		//페이징처리 설정 끝
		
		List<FileBoardVO> testlist = new ArrayList<>();
		
		int sqlStartNum = pagemaker.getPagenum()*10-9;
		int sqlOutputNum = pagemaker.getPagenum()*pagemaker.getContentnum();
		
		System.out.println();
		testlist = pageMakerMapper.testlist(sqlStartNum, sqlOutputNum);
			
		System.out.println("몇 번 부터 출력할지 : "+sqlStartNum);
			System.out.println("처음부터 총 몇 개 출력할지 : "+sqlOutputNum);
			System.out.println();
			System.out.println("========");
			System.out.println();
		
		//model.addAttribute("fblist", fboardService.getFileBoardList());
		model.addAttribute("testlist", testlist);
		model.addAttribute("page", pagemaker);
		
		return "/fileBoard/list";
	}
	
	@RequestMapping("/detail/{b_no}")
	private String fileBoardDetail(@PathVariable("b_no") int b_no, Model model) {
		model.addAttribute("detail", fboardService.fileBoardDetail(b_no));
		model.addAttribute("files", fboardService.fileDetail(b_no));
		return "fileBoard/detail";
	}
	
	@RequestMapping("/insert")
	private String fileBoardInsertForm(@ModelAttribute FileBoardVO board) {
		return "fileBoard/insert";
	}
	
/*
	@RequestMapping("/insertProc")
	private int fileBoardInsertProc(HttpServletRequest request) {
		FileBoardVO board = (FileBoardVO) request.getParameterMap();
		return fboardService.fileBoardInsert(board);
	}
*/
	
/*	
	@RequestMapping("/insertProc")
	private String fileBoardInsertProc(HttpServletRequest request) {
		FileBoardVO board = new FileBoardVO();
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContent(request.getParameter("content"));
		
		fboardService.fileBoardInsert(board);
		
		return "redirect:/fileBoard/list";
	}
*/
	
	@RequestMapping("/insertProc")
	private String fileBoardInsertProc(@ModelAttribute FileBoardVO board, @RequestPart MultipartFile files) 
			throws IllegalStateException, IOException, Exception {	
		
		if(files.isEmpty()) {
			fboardService.fileBoardInsert(board);
			
		} else {
			String fileName = files.getOriginalFilename(); // 사용자 컴에 저장된 파일명 그대로
			String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase(); //확장자
			File destinationFile; // DB에 저장할 파일 고유명
			String destinationFileName;
			String fileUrl = "C:\\Users\\user\\Downloads\\UpdateFiles\\"; //절대경로 설정 안해주면 지 맘대로 들어가버려서 절대경로 박아주었습니다.

			do { //우선 실행 후
				destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension; //고유명 생성
				destinationFile = new File(fileUrl + destinationFileName); //합쳐주기
			} while (destinationFile.exists()); 

			destinationFile.getParentFile().mkdirs(); //디렉토리
			files.transferTo(destinationFile);

			fboardService.fileBoardInsert(board);

			UpdateFileVO file = new UpdateFileVO();
			file.setB_no(board.getB_no());
			file.setFilename(destinationFileName);
			file.setFileoriginname(fileName);
			file.setFileurl(fileUrl);
			
			fboardService.fileInsertService(file);
		}
		
		return "forward:/fileBoard/list"; //객체 재사용
	}
	
/*	
	@RequestMapping("/insertResult")
	private String insertResult(Model model, @ModelAttribute FileBoardVO board) {
		model.addAttribute("success", board.getWriter()+"님 글이 성공적으로 등록되었습니다.");
		return "fileBoard/InsertResult";
	}
*/	
	@RequestMapping("/update/{b_no}")
	private String fileBoardUpdateForm(@PathVariable("b_no") int b_no, Model model) {
		model.addAttribute("detail", fboardService.fileBoardDetail(b_no));
		return "fileBoard/update";
	}
	
	@RequestMapping("/updateProc")
	private String fileBoardUpdateProc(@ModelAttribute FileBoardVO board) {
		
		fboardService.fileBoardUpdate(board);
		int bno = board.getB_no();
		String b_no = Integer.toString(bno);
		
		return "redirect:/fileBoard/detail/"+b_no;
	}
	
	@RequestMapping("/delete/{b_no}")
	private String fileBoardDelete(@PathVariable("b_no") int b_no) {
		fboardService.fileBoardDelete(b_no);
		return "redirect:/fileBoard/list";
	}
	
	@RequestMapping("/fileDown/{b_no}")
	private void fileDown(@PathVariable("b_no") int b_no, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, Exception {
		request.setCharacterEncoding("UTF-8");
		UpdateFileVO fileVO = fboardService.fileDetail(b_no);
		
		//System.out.println(fileVO);
		//System.out.println("1");
		
		//파일 업로드 경로
		try {
			String fileUrl = fileVO.getFileurl();
			fileUrl += "/";
			String savePath = fileUrl;
			String fileName = fileVO.getFilename();

			System.out.println("2");
			
			//실제 내보낼 파일명
			 String originFileName = fileVO.getFileoriginname();
			 InputStream in = null;
			 OutputStream os = null;
			 File file= null;
			 Boolean skip = false;
			 String client = "";
			 
			 System.out.println("3");

			 //파일을 읽어 스트림에 담기
			try {
				file = new File(savePath, fileName);
				in = new FileInputStream(file);
				System.out.println("4");
			} catch (FileNotFoundException fe) {
				skip = true;
			} 

			System.out.println("5");
			client = request.getHeader("User-Agent");
			 
			//파일 다운로드 헤더 지정
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "HTML Generated Data");
			System.out.println("6");

			if(!skip) {
				//IE
				if(client.indexOf("MSIE") != -1) {
					response.setHeader("Content-Disposition", "attachment; filename=\"" 
						+ java.net.URLEncoder.encode(originFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
				//IE 11 이상
				} else if (client.indexOf("Trident") != -1) {
					response.setHeader("Content-Disposition", "attachment; filename=\""
						+ java.net.URLEncoder.encode(originFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
				//한글 파일명 처리
				} else {
					System.out.println("7");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(originFileName.getBytes("UTF-8"), "ISO8859_1") + "\"");
					response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
				 }
				System.out.println("8");
				 
				response.setHeader("Content-Length", ""+file.length());
				os = response.getOutputStream();
				byte b[] = new byte[(int) file.length()];
				int leng = 0;
				System.out.println("9");

				while ((leng = in.read(b)) > 0) {
					os.write(b, 0, leng);
				}
				System.out.println("10");
			} else {
				System.out.println("11");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script> alert('파일을 찾을 수 없습니다.'); history.back(); </script>");
				out.flush();
			}
			 
			 in.close();
			 os.close();
		
		} catch (Exception e) {
			System.out.println("ERROR : " + e.getStackTrace());
		}
		
	}
	
}
