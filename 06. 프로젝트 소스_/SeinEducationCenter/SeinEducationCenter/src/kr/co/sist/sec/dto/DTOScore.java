package kr.co.sist.sec.dto;

public class DTOScore {
	
	private String seq;
	private String opensubject_seq;
	private int attendance;
	private int written_test;
	private int performance_test;
	private String opencoursestudent_seq;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getOpensubject_seq() {
		return opensubject_seq;
	}
	public void setOpensubject_seq(String opensubject_seq) {
		this.opensubject_seq = opensubject_seq;
	}
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	public int getWritten_test() {
		return written_test;
	}
	public void setWritten_test(int written_test) {
		this.written_test = written_test;
	}
	public int getPerformance_test() {
		return performance_test;
	}
	public void setPerformance_test(int performance_test) {
		this.performance_test = performance_test;
	}
	public String getOpencoursestudent_seq() {
		return opencoursestudent_seq;
	}
	public void setOpencoursestudent_seq(String opencoursestudent_seq) {
		this.opencoursestudent_seq = opencoursestudent_seq;
	}
	
}
