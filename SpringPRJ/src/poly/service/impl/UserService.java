package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.NoticeDTO;
import poly.dto.UserDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;

@Service("UserService")
public class UserService implements IUserService{
	
	@Resource(name="UserMapper")
	private IUserMapper userMapper;

	@Override
	public UserDTO getUserInfo(UserDTO uDTO) {
		return userMapper.getUserInfo(uDTO);
	}

	@Override
	public List<NoticeDTO> getNoticeList() {
		return userMapper.getNoticeList();
	}

	@Override
	public int InsertNoticeInfo(NoticeDTO pDTO) {
		return userMapper.InsertNoticeInfo(pDTO);
	}

	@Override
	public void updateNoticeReadCnt(NoticeDTO pDTO) {
		userMapper.updateNoticeReadCnt(pDTO);
	}

	@Override
	public NoticeDTO getNoticeInfo(NoticeDTO pDTO) {
		return userMapper.getNoticeInfo(pDTO);
	}

	@Override
	public void updateNoticeInfo(NoticeDTO pDTO) {
		userMapper.updateNoticeInfo(pDTO);
	}

	@Override
	public void deleteNoticeInfo(NoticeDTO pDTO) {
		userMapper.deleteNoticeInfo(pDTO);
		
	}

}
