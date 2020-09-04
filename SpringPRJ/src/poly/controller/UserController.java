package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
			
		}
		url = "/index.do";
		
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
		
		url = "/";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		log.info(this.getClass()+ "user/userLogOut end!");
		
		return "/redirect";
	}
}
