package kr.co.sist.sec.dto;

// 선택한 교사 정보
public class DTOvwSelectedTeacher {

	private String seq; 				//교사번호
	private String name; 				//교사이름
	private String allocated_course; 	//배정된과정
	private String allocated_start; 	//과정시작일
	private String allocated_end;		//과정종료일
	private String classroom;			//강의실
	private String subject_name;		//과목명
	private String subject_start;		//과목시작일
	private String subject_end;			//과목종료일
	private String textbook;			//교재
	
	
	
	
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	
	
	public String getSubject_start() {
		return subject_start;
	}
	public void setSubject_start(String subject_start) {
		this.subject_start = subject_start;
	}
	
	
	public String getSubject_end() {
		return subject_end;
	}
	public void setSubject_end(String subject_end) {
		this.subject_end = subject_end;
	}
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getAllocated_course() {
		return allocated_course;
	}
	public void setAllocated_course(String allocated_course) {
		this.allocated_course = allocated_course;
	}
	
	
	public String getAllocated_start() {
		return allocated_start;
	}
	public void setAllocated_start(String allocated_start) {
		this.allocated_start = allocated_start;
	}
	
	
	public String getAllocated_end() {
		return allocated_end;
	}
	public void setAllocated_end(String allocated_end) {
		this.allocated_end = allocated_end;
	}
	
	
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	
	
	public String getTextbook() {
		return textbook;
	}
	public void setTextbook(String textbook) {
		this.textbook = textbook;
	}
	
}
