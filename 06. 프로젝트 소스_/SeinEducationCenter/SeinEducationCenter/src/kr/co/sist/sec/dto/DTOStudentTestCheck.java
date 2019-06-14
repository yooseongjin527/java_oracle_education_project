package kr.co.sist.sec.dto;

public class DTOStudentTestCheck {
	
// 교육생 - 성적조회
	
	private String name; // 교육생 이름
	private String Subject_name; // 과목명
	private String start_date; // 과목 시작일
	private String end_date; // 과목종료일
	private String textbook_name; // 교재명
	private String teacher_name; // 교사명
	private String Score_attendance; // 출결점수
	private String Score_written_test; // 필기점수
	private String Score_performance_test; // 실기점수
	private String OpenSubject_writtentest_date; // 필기시험날짜
	private String OpenSubject_performancetest_date; // 실기시험날짜	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject_name() {
		return Subject_name;
	}
	public void setSubject_name(String subject_name) {
		Subject_name = subject_name;
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
	public String getTextbook_name() {
		return textbook_name;
	}
	public void setTextbook_name(String textbook_name) {
		this.textbook_name = textbook_name;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getScore_attendance() {
		return Score_attendance;
	}
	public void setScore_attendance(String score_attendance) {
		Score_attendance = score_attendance;
	}
	public String getScore_written_test() {
		return Score_written_test;
	}
	public void setScore_written_test(String score_written_test) {
		Score_written_test = score_written_test;
	}
	public String getScore_performance_test() {
		return Score_performance_test;
	}
	public void setScore_performance_test(String score_performance_test) {
		Score_performance_test = score_performance_test;
	}
	public String getOpenSubject_writtentest_date() {
		return OpenSubject_writtentest_date;
	}
	public void setOpenSubject_writtentest_date(String openSubject_writtentest_date) {
		OpenSubject_writtentest_date = openSubject_writtentest_date;
	}
	public String getOpenSubject_performancetest_date() {
		return OpenSubject_performancetest_date;
	}
	public void setOpenSubject_performancetest_date(String openSubject_performancetest_date) {
		OpenSubject_performancetest_date = openSubject_performancetest_date;
	}
	
}
