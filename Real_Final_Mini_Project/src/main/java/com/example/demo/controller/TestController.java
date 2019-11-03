package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bean.smBoardVO;
import com.example.demo.dao.smPageMaker;
import com.example.demo.mapper.smMemberMapper;
@RequestMapping("/sm")
@Controller
public class TestController {
	@Autowired
	JavaMailSender sender;
	
	private void sendEmail(String title , String content) throws Exception{
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setTo("lishal12@naver.com");
		helper.setText(content);
		helper.setSubject(title);
		
		sender.send(message);
	}
	
	@RequestMapping("/FormMail")
	public void mail() {
		
	}
	
	@RequestMapping("/testR")
  public void home(String title , String content,Model model) {
        String lsm;
		try {
            sendEmail(title,content);
            lsm="Email Sent!";
        }catch(Exception ex) {
            lsm="Error in sending email: "+ex;
        }
		model.addAttribute("email", lsm);
    }
 
	@Autowired
	public smMemberMapper mapper;
	
	public void pagerobot(Model model) {
		int pagenum=1;
		smPageMaker pagemaker = new smPageMaker();
		pagemaker.setTotalcount(mapper.pcount());//전체 게시글 개수를 지정한다.
		pagemaker.setPagenum(pagenum);//현재 페이지를 페이지 객체에 지정한다.-1을 해야 쿼리에서 사용할 수 있다.
		pagemaker.setCurrentblock(pagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
		pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
	
		pagemaker.prevnext(pagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
		pagemaker.setStartPage(pagemaker.getCurrentblock());//시작 페이지를 페이지 블록 번호로 정한다.
		pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
		//마지막 페이지를 마지막 페이지 블록과 현재 블록 번호로 정한다.
		
		List<smBoardVO> testlist = new ArrayList<smBoardVO>();
		testlist = mapper.plist((pagemaker.getPagenum()*10)-9, pagemaker.getPagenum()*10);
		model.addAttribute("list",testlist);
		model.addAttribute("page",pagemaker);
	}
	
	@GetMapping("test") //시작 컨트롤러 처음화면
	public String test(Model model) {
		pagerobot(model);
		return "/sm/test";
	}
	
	@GetMapping("test1") //페이징 관련 컨트롤러 절대 건들지 않기 !!
	public String test1(Model model ,int pagenum) {
		
		smPageMaker pagemaker = new smPageMaker();
		pagemaker.setTotalcount(mapper.pcount());//전체 게시글 개수를 지정한다.
		pagemaker.setPagenum(pagenum);//현재 페이지를 페이지 객체에 지정한다.-1을 해야 쿼리에서 사용할 수 있다.
		pagemaker.setCurrentblock(pagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
		pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
	
		pagemaker.prevnext(pagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
		pagemaker.setStartPage(pagemaker.getCurrentblock());//시작 페이지를 페이지 블록 번호로 정한다.
		pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
		//마지막 페이지를 마지막 페이지 블록과 현재 블록 번호로 정한다.
		
		List<smBoardVO> testlist = new ArrayList<smBoardVO>();
		testlist = mapper.plist((pagemaker.getPagenum()*10)-9, pagemaker.getPagenum()*10);
		model.addAttribute("list",testlist);
		model.addAttribute("page",pagemaker);
		

		return "/sm/test";
	}
	
	
	
	
	@GetMapping("test2") //검색 + 페이징 관련 컨트롤러로 활용
	public String test2(Model model ,int pagenum,String key,String search) {
		List<smBoardVO> testlist = new ArrayList<smBoardVO>();
		smPageMaker pagemaker = new smPageMaker();
		
		if(key.equals("글쓴이")) {
			pagemaker.setTotalcount(mapper.CWtitle(search));//전체 게시글 개수를 지정한다.
			pagemaker.setPagenum(pagenum);//현재 페이지를 페이지 객체에 지정한다.-1을 해야 쿼리에서 사용할 수 있다.
			pagemaker.setCurrentblock(pagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
			pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
			pagemaker.prevnext(pagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
			pagemaker.setStartPage(pagemaker.getCurrentblock());//시작 페이지를 페이지 블록 번호로 정한다.
			pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
			testlist = mapper.Wtitle(search,(pagemaker.getPagenum()*10)-9, pagemaker.getPagenum()*10);
			
			
			model.addAttribute("list",testlist);
			model.addAttribute("page",pagemaker);
			model.addAttribute("key", key);
			model.addAttribute("search", search);
		}else {
			pagemaker.setTotalcount(mapper.CWtitle(search));//전체 게시글 개수를 지정한다.
			pagemaker.setPagenum(pagenum);//현재 페이지를 페이지 객체에 지정한다.-1을 해야 쿼리에서 사용할 수 있다.
			pagemaker.setCurrentblock(pagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
			pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
			pagemaker.prevnext(pagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
			pagemaker.setStartPage(pagemaker.getCurrentblock());//시작 페이지를 페이지 블록 번호로 정한다.
			pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
			testlist = mapper.Stitle(search,(pagemaker.getPagenum()*10)-9, pagemaker.getPagenum()*10);
			
			
			model.addAttribute("list",testlist);
			model.addAttribute("page",pagemaker);
			model.addAttribute("key", key);
			model.addAttribute("search", search);
		}
		
		return "/sm/test2";
	}
	
	
	
	
	@GetMapping("boardView")
	public String CC(Model model,int idx) {
		smBoardVO board = mapper.smselectIdx(idx);
		board.setView_cnt(board.getView_cnt()+1);//조회수 증가
		mapper.smupdateBoard(board);
		model.addAttribute("board", board);
		return "/sm/boardView";
	}
	
	@GetMapping("update")
	public String Update(int idx ,String title ,String content,String writer,Model model) {
			pagerobot(model);
			smBoardVO lsm = mapper.smselectIdx(idx);
			lsm.setTitle(title);
			lsm.setContent(content);
			lsm.setWriter(writer);
			mapper.smupdateBoard(lsm);
			return "redirect:/sm/test";
	}
	
	@GetMapping("/delete")
	public String delete(int idx,Model model) {
		mapper.smdeleteBoard(idx);
		pagerobot(model);
		return "redirect:/sm/test";
	}
	@GetMapping("/create")
	public String create() {
		return "/sm/create";
	}
	
	@GetMapping("/create1")
	public String create1(Model model,smBoardVO board) {
		mapper.sminsertBoard(board);
		pagerobot(model);
		return "redirect:/sm/test";
	}
	@GetMapping("search")
	public String search(Model model,String option,String search) {
		int pagenum=1;
		List<smBoardVO> testlist = new ArrayList<smBoardVO>();
		smPageMaker pagemaker = new smPageMaker();
		
		if(option.equals("글쓴이")) {
			pagemaker.setTotalcount(mapper.CWtitle(search));//전체 게시글 개수를 지정한다.
			pagemaker.setPagenum(pagenum);//현재 페이지를 페이지 객체에 지정한다.-1을 해야 쿼리에서 사용할 수 있다.
			pagemaker.setCurrentblock(pagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
			pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
			pagemaker.prevnext(pagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
			pagemaker.setStartPage(pagemaker.getCurrentblock());//시작 페이지를 페이지 블록 번호로 정한다.
			pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
			testlist = mapper.Wtitle(search,(pagemaker.getPagenum()*10)-9, pagemaker.getPagenum()*10);
			
			
			model.addAttribute("list",testlist);
			model.addAttribute("page",pagemaker);
			model.addAttribute("key", option);
			model.addAttribute("search", search);
		}else {
			pagemaker.setTotalcount(mapper.CStitle(search));//전체 게시글 개수를 지정한다.
			pagemaker.setPagenum(pagenum);//현재 페이지를 페이지 객체에 지정한다.-1을 해야 쿼리에서 사용할 수 있다.
			pagemaker.setCurrentblock(pagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
			pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
			pagemaker.prevnext(pagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
			pagemaker.setStartPage(pagemaker.getCurrentblock());//시작 페이지를 페이지 블록 번호로 정한다.
			pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
			testlist = mapper.Stitle(search,(pagemaker.getPagenum()*10)-9, pagemaker.getPagenum()*10);
			
			
			model.addAttribute("list",testlist);
			model.addAttribute("page",pagemaker);
			model.addAttribute("key", option);
			model.addAttribute("search", search);
		}
			
		
		
		return "/sm/test2";
	}
}
