package kr.co.sist.sec.dto;

public class DTOScoreInformation {
	
	private int num;
	private String name;
	private String subject_name;
	private String quit_status;
	private int attendance_score;
	private int writtentest_score;
	private int performance_score;
	private int total;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	
	public int getWrittentest_score() {
		return writtentest_score;
	}
	public void setWrittentest_score(int writtentest_score) {
		this.writtentest_score = writtentest_score;
	}
	public int getPerformance_score() {
		return performance_score;
	}
	public void setPerformance_score(int performance_score) {
		this.performance_score = performance_score;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getQuit_status() {
		return quit_status;
	}
	public void setQuit_status(String quit_status) {
		this.quit_status = quit_status;
	}
	public int getAttendance_score() {
		return attendance_score;
	}
	public void setAttendance_score(int attendance_score) {
		this.attendance_score = attendance_score;
	}
	
	
}
