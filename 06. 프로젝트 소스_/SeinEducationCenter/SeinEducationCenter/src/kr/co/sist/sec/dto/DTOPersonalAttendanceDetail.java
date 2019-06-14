package kr.co.sist.sec.dto;

public class DTOPersonalAttendanceDetail {
	private String geuntae_date;
	private String course_name;
	private String student_name;
	private String geuntae_status;
	
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getStudent_name() {
		return student_name;
	}
	
	public void setStudent_name (String student_name) {
		this.student_name = student_name;
	}
	
	public String getGeuntae_status() {
		return geuntae_status;
	}
	
	public void setGeuntae_status(String geuntae_status) {
		this.geuntae_status = geuntae_status;
	}
	public String getGeuntae_date() {
		return geuntae_date;
	}
	public void setGeuntae_date(String geuntae_date) {
		this.geuntae_date = geuntae_date;
	}
	
	

}
