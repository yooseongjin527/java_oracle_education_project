package kr.co.sist.sec.dto;

public class DTONotice {
	private String seq;
	private String company_seq;
	private String salary;       
	private String deadline;
	private String course;
	private String major;
	
	
	public String getSeq() {
		return seq;
	}
	
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	public String getCompany_seq() {
		return company_seq;
	}
	
	public void setCompany_seq(String company_seq) {
		this.company_seq = company_seq;
	}
	
	public String getSalary() {
		return salary;
	}
	
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	public String getDeadline() {
		return deadline;
	}
	
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	public String getCourse() {
		return course;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	
}
