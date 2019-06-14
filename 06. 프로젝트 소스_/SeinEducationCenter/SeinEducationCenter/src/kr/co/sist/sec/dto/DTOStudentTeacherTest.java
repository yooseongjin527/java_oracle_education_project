package kr.co.sist.sec.dto;

public class DTOStudentTeacherTest {

	//교육생 - 교사평가
	
	private String course_seq; // 과정번호 
	private String student_seq; // 교육생 번호
	private String student_name; // 교육생 이름
	private String status; // 상태 
	private String geuntae_date; // 날짜
	
	
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
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
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
