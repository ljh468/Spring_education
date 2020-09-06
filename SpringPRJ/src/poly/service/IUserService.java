package poly.service;

import java.util.List;

import poly.dto.NoticeDTO;
import poly.dto.UserDTO;

public interface IUserService {

	UserDTO getUserInfo(UserDTO uDTO);

	List<NoticeDTO> getNoticeList();

	int InsertNoticeInfo(NoticeDTO pDTO);

	void updateNoticeReadCnt(NoticeDTO pDTO);

	NoticeDTO getNoticeInfo(NoticeDTO pDTO);

	void updateNoticeInfo(NoticeDTO pDTO);

	void deleteNoticeInfo(NoticeDTO pDTO);
	
}
