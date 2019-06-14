package kr.co.sist.sec.dto;

public class DTOTeacher_2 {
		// 교사 - 선생님이름 / 날짜 / 과목 조회
	
		private String Teacher_seq; // 교사 번호
		private String Teacher_name; // 교사 이름
		private String Course_name; // 배정된 과정명
		private String OpenCourse_start_date; // 과정 시작일
		private String OpenCourse_end_date; // 과정 종료일
		private String subject_name; // 배정 개설과목명
		private String OpenSubject_start_date; // 과목 시작일
		private String OpenSubject_end_date; // 과목 종료일
		private String Classroom_name; // 강의실명
		private String Textbook_name; // 교재명
	    private String OpenCourse_seq; //인원수
		
	    
	    public String getTeacher_seq() {
			return Teacher_seq;
		}
		public void setTeacher_seq(String teacher_seq) {
			Teacher_seq = teacher_seq;
		}
		public String getTeacher_name() {
			return Teacher_name;
		}
		public void setTeacher_name(String teacher_name) {
			Teacher_name = teacher_name;
		}
		public String getCourse_name() {
			return Course_name;
		}
		public void setCourse_name(String course_name) {
			Course_name = course_name;
		}
		public String getOpenCourse_start_date() {
			return OpenCourse_start_date;
		}
		public void setOpenCourse_start_date(String openCourse_start_date) {
			OpenCourse_start_date = openCourse_start_date;
		}
		public String getOpenCourse_end_date() {
			return OpenCourse_end_date;
		}
		public void setOpenCourse_end_date(String openCourse_end_date) {
			OpenCourse_end_date = openCourse_end_date;
		}
		public String getSubject_name() {
			return subject_name;
		}
		public void setSubject_name(String subject_name) {
			this.subject_name = subject_name;
		}
		public String getOpenSubject_start_date() {
			return OpenSubject_start_date;
		}
		public void setOpenSubject_start_date(String openSubject_start_date) {
			OpenSubject_start_date = openSubject_start_date;
		}
		public String getOpenSubject_end_date() {
			return OpenSubject_end_date;
		}
		public void setOpenSubject_end_date(String openSubject_end_date) {
			OpenSubject_end_date = openSubject_end_date;
		}
		public String getClassroom_name() {
			return Classroom_name;
		}
		public void setClassroom_name(String classroom_name) {
			Classroom_name = classroom_name;
		}
		public String getTextbook_name() {
			return Textbook_name;
		}
		public void setTextbook_name(String textbook_name) {
			Textbook_name = textbook_name;
		}
		public String getOpenCourse_seq() {
			return OpenCourse_seq;
		}
		public void setOpenCourse_seq(String openCourse_seq) {
			OpenCourse_seq = openCourse_seq;
		}


}
