package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.istack.internal.logging.Logger;

import poly.dto.BoardDTO;
import poly.service.IBoardService;

@Controller
public class BoardController {
	private Logger log = Logger.getLogger(getClass());
	
	// Controller와 Service를 연결
	@Resource(name="BoardService")
	IBoardService boardService; //가져올때 사용할 인터페이스타입의 변수 boardService설정
	
	@RequestMapping(value="/board/boardList")
	public String boardList(ModelMap model) {
		
		List<BoardDTO> rList = boardService.getBoardList();
		
		if(rList == null) {
			rList = new ArrayList<>();
		}
		model.addAttribute("rList",rList);
		return "/board/boardList";
	}
	
	@RequestMapping(value="/board/newPost")
	public String newPost(ModelMap model) {

		return "/board/newPost";
	}
	
	@RequestMapping(value="/board/doPost")
	public String doPost(HttpServletRequest request, ModelMap model){
		// 임시로 설정하는 아이디
		String id = "admin";
		
		// 요청으로부터 받은 파라미터를 변수에 저장
		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("post_content");
		
		// 게시자, 게시글제목, 게시글내용을 담아 서비스에 전송할 DTO객체 생성
		BoardDTO pDTO = new BoardDTO();
		
		// BoardDTO객체의  Reg_id에 request로 전송받은 데이터를 저장
		pDTO.setReg_id(id);
		pDTO.setPost_title(post_title);
		pDTO.setPost_content(post_content);
		
		// pDTO안에 있는 내용을 가지고 게시글을 등록 해달라고 BoardService에 요청
		// 등록에 성공하면 res에 반환값1을 받음
		int res = boardService.insertPost(pDTO);
		
		String msg = "";
		String url = "/board/boardList.do";
		
		if(res>0) {
			msg = "등록에 성공했습니다.";
		}else {
			msg = "등록에 실패했습니다.";
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "/redirect";
	}
}













