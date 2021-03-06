package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.EmpDTO;
import poly.service.IEmpService;


@Controller
public class EmpController {
	
	// 콘솔에 로그를 기록하기 위한 객체
	private Logger log = Logger.getLogger(this.getClass());
	
	// Controller와 Service를 연결 (직통전화선 구축)
	@Resource(name="EmpService")
	// 서비스를 구현하는 IEmpService타입의 empService변수
	IEmpService empService;
	
	
	@RequestMapping(value="/scott/empList")
	public String empList(ModelMap model) {
		log.info("empList start!!");
		// EmpDTO타입의 rList에 EmpService에서 가져온데이터를 저장
		List<EmpDTO> rList = empService.getEmpList();
		
		if(rList == null) {
			rList = new ArrayList<>();
		}
		for(EmpDTO eDTO : rList) {
			log.info("ename : " + eDTO.getEname());
			log.info("empno : " + eDTO.getEmpno());
		}
		model.addAttribute("rList",rList);
		
		log.info("empList end!!");
		return "/scott/empList";
	}
	
	@RequestMapping(value="/scott/managerList")
	public String managerList(ModelMap model) {
		List<EmpDTO> rList = empService.getmanagerList();
		
		if(rList == null) {
			rList = new ArrayList<>();
		}
		model.addAttribute("rList", rList);
		
		return "/scott/empList";
		
	}
			
}
