package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
	
	// 콘솔에 로그를 기록하기 위한 객체
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="index") // index.do라는 요청이 들어왔을때 밑의 메서드가 실행되게 하는 어노테이션
	public String Index() {
		
		// 해당 메서드가 호출될때 해당메서트의 클래스이름을 로그형태로 출력
		log.info(this.getClass());
		
		return "/index";
	}
	
	@RequestMapping(value="practice.do")
	public String Practice()	{
		
		log.info(this.getClass());
		return "/practice";
	}
			
}
