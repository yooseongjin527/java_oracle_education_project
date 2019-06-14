package kr.co.sist.sec.dto;

public class DTOStudentBoard {

	//교육생 - 게시판
	
	private  String board_seq; // 글번호
	private  String student_name; // 작성자
	private  String board_regdate; // 날짜
	private  String board_title; // 제목
	private  String board_content; // 내용
	
	public  String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public  String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public  String getBoard_regdate() {
		return board_regdate;
	}
	public void setBoard_regdate(String board_regdate) {
		this.board_regdate = board_regdate;
	}
	public  String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public  String getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(String board_seq) {
		this.board_seq = board_seq;
	}
    
}
