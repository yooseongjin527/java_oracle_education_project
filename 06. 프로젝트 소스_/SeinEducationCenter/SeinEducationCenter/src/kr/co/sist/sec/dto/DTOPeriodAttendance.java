package kr.co.sist.sec.dto;

public class DTOPeriodAttendance {
	
	private String geuntae_date;
	private String opencourse_name;
	private String student_name;
	private String status;
	
	
	public String getOpencourse_name() {
		return opencourse_name;
	}
	public void setOpencourse_name(String opencourse_name) {
		this.opencourse_name = opencourse_name;
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
