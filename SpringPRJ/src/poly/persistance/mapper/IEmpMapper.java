package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.DummyDTO;
import poly.dto.EmpDTO;

@Mapper("EmpMapper") // EmpMapper.xml이라는 파일에 메서드가 구현되어있다고 스프링에 알림
public interface IEmpMapper {

	List<EmpDTO> getEmpList();

	List<EmpDTO> getmanagerList();
	
}
