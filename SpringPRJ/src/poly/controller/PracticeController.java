package poly.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static poly.util.CmmUtil.nvl;

@Controller
public class PracticeController {
	
	// 로깅을 위한 객체
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="table")
	public String Practice() {
		//해당 메서드가 호출됨을 로그로 기록
		log.info(this.getClass());
		return "/table";
	}
	
	@RequestMapping(value="get")
	public String get(HttpServletRequest request, ModelMap model)throws Exception{
		String name = nvl(request.getParameter("name"));
		
		log.info(name);
		model.addAttribute("getgo", name);
		return "/get";
	}
	
	@RequestMapping(value="post")
	public String post() {
		log.info(this.getClass());
		return "/postForm";
	}
	
	@RequestMapping(value="doPost", method = RequestMethod.POST)
	public String doPost(HttpServletRequest request, ModelMap model) throws Exception{
			String name = nvl(request.getParameter("htname"));
			
			log.info(name);
			model.addAttribute("getgo", name);
		return "/get";
	}
}
