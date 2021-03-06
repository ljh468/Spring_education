package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.EmpDTO;
import poly.persistance.mapper.IEmpMapper;
import poly.service.IEmpService;

//자신이 EmpService라는 서비스 객체임을 스프링 프레임워크에 알림
@Service("EmpService")
public class EmpService implements IEmpService{ // IEmpService 인터페이스를 구현함
	
	@Resource(name="EmpMapper")
	IEmpMapper empMapper;
	
	// EmpService에 getEmpList메서드가 추가됨
	@Override
	public List<EmpDTO> getEmpList() {
		
		return empMapper.getEmpList();
		}

	@Override
	public List<EmpDTO> getmanagerList() {
		return empMapper.getmanagerList();
	}  
}