package kr.co.sist.sec.dto;

// 선택된 교육생 정보
public class DTOvwSelectedStudent {
	
	private String seq;				//번호
	private String name;			//이름
	private String ssn;				//주민번호뒷자리
	private String tel;				//전화번호
	private String regdate;			//등록일
	private String major;			//전공여부
	private String course_status;	//수강상태
	private String course_name;		//과정명
	private String course_start;	//과정시작일
	private String course_end;		//과정종료일
	private String classroom;		//강의실
	
	
	
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
	
	
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	
	public String getCourse_status() {
		return course_status;
	}
	public void setCourse_status(String course_status) {
		this.course_status = course_status;
	}
	
	
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
	
	public String getCourse_start() {
		return course_start;
	}
	public void setCourse_start(String course_start) {
		this.course_start = course_start;
	}
	
	
	public String getCourse_end() {
		return course_end;
	}
	public void setCourse_end(String course_end) {
		this.course_end = course_end;
	}
	
	
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	
}
