package kr.co.sist.sec.dto;

public class DTOTestManage {
	
	private String course_seq;
	private String course_name;
	private String start_date;
	private String end_date;
	private String classroom_name;
	
	public String getCourse_name() {
		return course_name;
	}
	
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
	public String getStart_date() {
		return start_date;
	}
	
	public void setStart_date(String start_date) {	
		this.start_date = start_date;
	}
	
	public String getEnd_date() {
		return end_date;
	}
	
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	public String getClassroom_name() {
		return classroom_name;
	}
	
	public void setClassroom_name(String classroom_name) {
		this.classroom_name = classroom_name;
	}

	public String getCourse_seq() {
		return course_seq;
	}

	public void setCourse_seq(String course_seq) {
		this.course_seq = course_seq;
	}
	
}
