package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.NoticeDTO;
import poly.dto.UserDTO;
import poly.service.IUserService;
import poly.util.CmmUtil;


@Controller
public class UserController {
	
	// 콘솔에 로그를 기록하기 위한 객체
	private Logger log = Logger.getLogger(this.getClass());
	
@Resource(name="UserService")
private IUserService userService;

	@RequestMapping(value="user/userLogin")
	public String userLogin() {
		log.info(this.getClass()+"user/userLogin start!!");
		log.info(this.getClass()+"user/userLogin end!!");
		return "/user/userLogin";
	}

	@RequestMapping(value="user/userLoginProc")
	public String userLoginProc(HttpServletRequest request, ModelMap model, HttpSession session) {
		
		// 해당 메서드가 호출될때 해당메서트의 클래스이름을 로그형태로 출력
		log.info(this.getClass()+"user/userLogin start!");
		
		// jsp 폼태그에서 보낸 값을 String으로 받아줌
		String id = CmmUtil.nvl(request.getParameter("id"));
		String pwd = CmmUtil.nvl(request.getParameter("pwd"));
		
		log.info("id:" +id);
		log.info("pwd:" +pwd);
		
		// DTO생성
		UserDTO uDTO = new UserDTO();
		uDTO.setUser_id(id);
		uDTO.setUser_pwd(pwd);
		
		// 데이터베이스로 id, pwd를 보내고
		uDTO = userService.getUserInfo(uDTO);
		
		log.info("uDTO null? : "+(uDTO == null));
		String msg = "";
		String url="";
		if(uDTO == null) {
			msg = "로그인실패";
		}else {
			log.info("uDTO seq : " + uDTO.getUser_seq());
			log.info("uDTO id : " + uDTO.getUser_id());
			log.info("uDTO name : " + uDTO.getUser_name());
			
			msg = "로그인성공";
			
			session.setAttribute("user_seq", uDTO.getUser_seq());
			session.setAttribute("user_name", uDTO.getUser_name());
			session.setAttribute("user_id", uDTO.getUser_id());
			
		}
		url = "/notice/NoticeList.do";
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		log.info(this.getClass()+"user/userLoginProc end!");
		
		return "/redirect";
	}
	
	@RequestMapping(value="/user/userLogOut")
	public String userLogout(HttpSession session, ModelMap model)throws Exception{
		log.info(this.getClass()+ "user/userLogOut start!");
		
		String msg = "";
		String url = "";
		
		msg = "로그아웃 성공";
		
		session.invalidate();
		// session을 비움
		
		url = "/notice/NoticeList.do";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		log.info(this.getClass()+ "user/userLogOut end!");
		
		return "/redirect";
	}
	
	// 공지사항 만들기
	@RequestMapping(value="/notice/NoticeList", method=RequestMethod.GET)
	public String NoticeList(HttpServletRequest request, HttpServletResponse response, ModelMap model,HttpSession session)throws Exception{
		// 로그찍기
		log.info(this.getClass()+"NoticeList start!!");
		
		// 공지사항 리스트 가져오기
		List<NoticeDTO> rList = userService.getNoticeList();
		
		if(rList==null) {
			rList = new ArrayList<NoticeDTO>();
		}
		
		// 조회된 결과값을 jsp에 보내기위해  model에 넣어줌
		model.addAttribute("rList", rList);
		
		// 변수초기과
		rList = null;
		
		log.info(this.getClass()+"NoticeList end!!");
		return "/notice/NoticeList";
	}
	
	// 리스트 작성페이지
	@RequestMapping(value="notice/NoticeReg", method=RequestMethod.GET)
	public String NoticeReg(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeReg start!");
		
		log.info(this.getClass().getName() + ".NoticeReg end!");
		
		return "/notice/NoticeReg";
	}
	
	/**
	 * 게시판 글 등록
	 * */
	@RequestMapping(value="notice/NoticeInsert", method=RequestMethod.GET)
	public String NoticeInsert(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeInsert start!");
		
			/*
			 * 게시판 글 등록되기 위해 사용되는 form객체의 하위 input 객체 등을 받아오기 위해 사용함
			 * */
			String user_id = CmmUtil.nvl((String)session.getAttribute("user_id")); //아이디
			String title = CmmUtil.nvl(request.getParameter("title")); //제목
			String noticeYn = CmmUtil.nvl(request.getParameter("noticeYn")); //공지글 여부
			String contents = CmmUtil.nvl(request.getParameter("contents")); //내용
	
			/*
			 * 
			 * #######################################################
			 * 	 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함
			 * 						반드시 작성할 것
			 * #######################################################
			 * 
			 */
			
			log.info("user_id : "+ user_id);
			log.info("title : "+ title);
			log.info("noticeYn : "+ noticeYn);
			log.info("contents : "+ contents);		
			
			NoticeDTO pDTO = new NoticeDTO();
			
			pDTO.setUser_id(user_id);
			pDTO.setTitle(title);
			pDTO.setNotice_yn(noticeYn);;
			pDTO.setContents(contents);
			
			/*
			 * 게시글 등록하기위한 비즈니스 로직을 호출
			 * */
			int res = userService.InsertNoticeInfo(pDTO);
			log.info("res : " + res);
			
			String msg = "";
			String url = "/notice/NoticeList.do";
			
			if(res>0) {
			msg = "등록되었습니다.";
			}else
			msg = "등록에 실패했습니다.";
	
			//결과 메시지 전달하기
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			return "/redirect";
		}
		
	
	/**
	 * 게시판 상세보기
	 * */
	@RequestMapping(value="notice/NoticeInfo", method=RequestMethod.GET)
	public String NoticeInfo(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeInfo start!");
		

		/*
		 * 게시판 글 등록되기 위해 사용되는 form객체의 하위 input 객체 등을 받아오기 위해 사용함
		 * */
		String nSeq = CmmUtil.nvl(request.getParameter("nSeq")); //공지글번호(PK)

		/*
		 * #######################################################
		 * 	 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함
		 * 						반드시 작성할 것
		 * #######################################################
		 * */
		log.info("nSeq : "+ nSeq);
		
		
		/*
		 * 값 전달은 반드시 DTO 객체를 이용해서 처리함
		 * 전달 받은 값을 DTO 객체에 넣는다.
		 * */		
		NoticeDTO pDTO = new NoticeDTO();

		
		pDTO.setNotice_seq(nSeq);;		
		
		//공지사항 글 조회수 증가
		userService.updateNoticeReadCnt(pDTO);
		
		log.info("read_cnt update success!!!");
		
		//공지사항 상세정보 가져오기
		NoticeDTO rDTO = userService.getNoticeInfo(pDTO);
		
		if (rDTO==null){
			rDTO = new NoticeDTO();
			
		}
		
		log.info("getNoticeInfo success!!!");
		
		//조회된 리스트 결과값 넣어주기
		model.addAttribute("rDTO", rDTO);
		
		//변수 초기화(메모리 효율화 시키기 위해 사용함)
		rDTO = null;
		pDTO = null;
		
		log.info(this.getClass().getName() + ".NoticeInfo end!");
		
		return "/notice/NoticeInfo";
	}
	
	
	/**
	 * 게시판 수정 보기
	 * */
	@RequestMapping(value="notice/NoticeEditInfo", method=RequestMethod.GET)
	public String NoticeEditInfo(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeEditInfo start!");
		
		
		String nSeq = CmmUtil.nvl(request.getParameter("nSeq")); //공지글번호(PK)
		
		log.info("nSeq : "+ nSeq);
		
		
		NoticeDTO pDTO = new NoticeDTO();
		
		pDTO.setNotice_seq(nSeq);;		
		
		/*
		 * #######################################################
		 * 	공지사항 수정정보 가져오기(상세보기 쿼리와 동일하여, 같은 서비스 쿼리 사용함)
		 * #######################################################
		 */
		NoticeDTO rDTO = userService.getNoticeInfo(pDTO);
		
		if (rDTO==null){
			rDTO = new NoticeDTO();
			
		}
		
		//조회된 리스트 결과값 넣어주기
		model.addAttribute("rDTO", rDTO);
		
		
		//변수 초기화(메모리 효율화 시키기 위해 사용함)
		rDTO = null;
		pDTO = null;
		
		log.info(this.getClass().getName() + ".NoticeEditInfo end!");
		
		return "/notice/NoticeEditInfo";
	}
	
	
	/**
	 * 게시판 글 수정
	 * */
	@RequestMapping(value="notice/NoticeUpdate", method=RequestMethod.POST)
	public String NoticeUpdate(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeUpdate start!");
		
		String msg = "";
		String url = "";
		try{
			
			String user_id = CmmUtil.nvl((String)session.getAttribute("user_id")); //아이디
			String nSeq = CmmUtil.nvl(request.getParameter("nSeq")); //글번호(PK)
			String title = CmmUtil.nvl(request.getParameter("title")); //제목
			String noticeYn = CmmUtil.nvl(request.getParameter("noticeYn")); //공지글 여부
			String contents = CmmUtil.nvl(request.getParameter("contents")); //내용
	
			log.info("user_id : "+ user_id);
			log.info("nSeq : "+ nSeq);
			log.info("title : "+ title);
			log.info("noticeYn : "+ noticeYn);
			log.info("contents : "+ contents);		
			
			NoticeDTO pDTO = new NoticeDTO();
			
			pDTO.setUser_id(user_id);
			pDTO.setNotice_seq(nSeq);;
			pDTO.setTitle(title);
			pDTO.setNotice_yn(noticeYn);;
			pDTO.setContents(contents);
	
			//게시글 수정하기 DB
			userService.updateNoticeInfo(pDTO);
			
			msg = "수정되었습니다.";
			url = "/notice/NoticeList.do";
			
			//변수 초기화(메모리 효율화 시키기 위해 사용함)
			pDTO = null;
			
		}catch(Exception e){
			msg = "실패하였습니다. : "+ e.toString();
			log.info(e.toString());
			e.printStackTrace();
			url = "/notice/NoticeList.do";
		}finally{
			log.info(this.getClass().getName() + ".NoticeUpdate end!");
			
			//결과 메시지 전달하기
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
		}
		
		return "/redirect";
	}	
	
	
	/**
	 * 게시판 글 삭제
	 * */
	@RequestMapping(value="notice/NoticeDelete", method=RequestMethod.GET)
	public String NoticeDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeDelete start!");
		
		String msg = "";
		String url = "";
		try{
			
			String nSeq = CmmUtil.nvl(request.getParameter("nSeq")); //글번호(PK)
			
			log.info("nSeq : "+ nSeq);
			
			NoticeDTO pDTO = new NoticeDTO();
			
			pDTO.setNotice_seq(nSeq);;
			
			//게시글 삭제하기 DB
			userService.deleteNoticeInfo(pDTO);;
			
			msg = "삭제되었습니다.";
			url = "/notice/NoticeList.do";
			//변수 초기화(메모리 효율화 시키기 위해 사용함)
			pDTO = null;
			
		}catch(Exception e){
			msg = "실패하였습니다. : "+ e.toString();
			log.info(e.toString());
			e.printStackTrace();
			url = "/notice/NoticeList.do";
		}finally{
			log.info(this.getClass().getName() + ".NoticeDelete end!");
			
			//결과 메시지 전달하기
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
		}
		
		return "/redirect";
	}	
	
	
}