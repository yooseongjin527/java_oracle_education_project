package kr.co.sist.sec.dto;

public class DTOCourseAttendance {
	private String geuntae_date;
	private String course_seq;
	private String course_name;
	private String attendance;
	private String late;
	private String go_out;
	private String absence;
	
	
	public String getGeuntae_date() {
		return geuntae_date;
	}
	public void setGeuntae_date(String geuntae_date) {
		this.geuntae_date = geuntae_date;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getLate() {
		return late;
	}
	public void setLate(String late) {
		this.late = late;
	}
	public String getGo_out() {
		return go_out;
	}
	public void setGo_out(String go_out) {
		this.go_out = go_out;
	}
	public String getAbsence() {
		return absence;
	}
	public void setAbsence(String absence) {
		this.absence = absence;
	}
	public String getCourse_seq() {
		return course_seq;
	}
	public void setCourse_seq(String course_seq) {
		this.course_seq = course_seq;
	}
	
	
	
	
}
