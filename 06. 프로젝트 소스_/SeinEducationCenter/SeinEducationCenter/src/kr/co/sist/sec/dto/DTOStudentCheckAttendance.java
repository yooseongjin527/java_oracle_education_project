package kr.co.sist.sec.dto;

public class DTOStudentCheckAttendance {

	//교육생 - 출결 조회
	
	private String course_seq; // 과정번호 
	private String student_seq; // 교육생 번호
	private String name; // 교육생 이름 
	private String status; // 상태 
	private String geuntae_date; // 날짜
	private String check_in; //입실시간
	private String check_out; //퇴실시간
	
	
	public String getCheck_in() {
		return check_in;
	}
	public void setCheck_in(String check_in) {
		this.check_in = check_in;
	}
	public String getCheck_out() {
		return check_out;
	}
	public void setCheck_out(String check_out) {
		this.check_out = check_out;
	}
	public String getCourse_seq() {
		return course_seq;
	}
	public void setCourse_seq(String course_seq) {
		this.course_seq = course_seq;
	}
	public String getStudent_seq() {
		return student_seq;
	}
	public void setStudent_seq(String student_seq) {
		this.student_seq = student_seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGeuntae_date() {
		return geuntae_date;
	}
	public void setGeuntae_date(String geuntae_date) {
		this.geuntae_date = geuntae_date;
	}
	
}
