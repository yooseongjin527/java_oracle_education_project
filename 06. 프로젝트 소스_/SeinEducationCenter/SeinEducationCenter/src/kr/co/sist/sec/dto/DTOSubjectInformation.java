package kr.co.sist.sec.dto;

public class DTOSubjectInformation {
	
	private String seq;
	private String subject_name;
	private String subject_period;
	private String classroom;
	private String textbook_name;
	private int attendance_allot;
	private int writtentest_allot;
	private int performancetest_allot;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public String getSubject_period() {
		return subject_period;
	}
	public void setSubject_period(String subject_period) {
		this.subject_period = subject_period;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public String getTextbook_name() {
		return textbook_name;
	}
	public void setTextbook_name(String textbook_name) {
		this.textbook_name = textbook_name;
	}
	public int getAttendance_allot() {
		return attendance_allot;
	}
	public void setAttendance_allot(int attendance_allot) {
		this.attendance_allot = attendance_allot;
	}
	public int getWrittentest_allot() {
		return writtentest_allot;
	}
	public void setWrittentest_allot(int writtentest_allot) {
		this.writtentest_allot = writtentest_allot;
	}
	public int getPerformancetest_allot() {
		return performancetest_allot;
	}
	public void setPerformancetest_allot(int performancetest_allot) {
		this.performancetest_allot = performancetest_allot;
	}
	
	
	
}
