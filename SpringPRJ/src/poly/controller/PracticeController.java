package poly.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		model.addAttribute("name", name);
		return "/get";
	}
}
