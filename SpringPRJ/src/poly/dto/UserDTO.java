package poly.dto;

/**
 * @author 이협건
 * @version 1.1 공지사항 DTO
 */
public class UserDTO {
	
	private String user_seq; // 유저번호
	private String user_id; // 아이디
	private String user_pwd; // 비밀번호
	private String user_name; // 유저이름
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}
	



}
