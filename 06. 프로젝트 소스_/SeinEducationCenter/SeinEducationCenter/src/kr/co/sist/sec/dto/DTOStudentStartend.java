package kr.co.sist.sec.dto;

public class DTOStudentStartend {

	//교육생 입실 퇴실
	
	private String student_seq; // 학생번호
	private String student_name; // 학생명
	private String course_name; // 수강한과정명
	private String classroom_name; // 강의실
	private String geuntae_date; // 날짜
	private String status; // 출결상태
	
	
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
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getClassroom_name() {
		return classroom_name;
	}
	public void setClassroom_name(String classroom_name) {
		this.classroom_name = classroom_name;
	}
	public String getGeuntae_date() {
		return geuntae_date;
	}
	public void setGeuntae_date(String geuntae_date) {
		this.geuntae_date = geuntae_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
