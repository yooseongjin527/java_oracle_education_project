package kr.co.sist.sec.dto;

public class DTOAllSubjectScore {
	
	private String student_name;
	private String ssn;
	private String subject_seq;
	private String written_test;
	private String performance_test;
	
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getWritten_test() {
		return written_test;
	}
	public void setWritten_test(String written_test) {
		this.written_test = written_test;
	}
	public String getPerformance_test() {
		return performance_test;
	}
	public void setPerformance_test(String performance_test) {
		this.performance_test = performance_test;
	}
	public String getSubject_seq() {
		return subject_seq;
	}
	public void setSubject_seq(String subject_seq) {
		this.subject_seq = subject_seq;
	}
	
	
	

}
