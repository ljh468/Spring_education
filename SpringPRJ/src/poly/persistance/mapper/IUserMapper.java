package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.DummyDTO;
import poly.dto.UserDTO;

@Mapper("UserMapper")
public interface IUserMapper {

	//게시판 리스트
	List<DummyDTO> getDummyList() throws Exception;

	UserDTO getUserInfo(UserDTO uDTO);
	
}
