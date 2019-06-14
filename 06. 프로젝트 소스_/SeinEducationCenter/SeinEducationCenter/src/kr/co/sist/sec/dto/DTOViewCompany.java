package kr.co.sist.sec.dto;

public class DTOViewCompany {

	// 교육생 - 공고 조회
	private String seq; // 공고 번호
	private String name; // 기업명,
	private String scale; // 계열_규모,
	private String employee_num; // 사원수
	private String established_year; // 연혁
	private String area; // 지역
	private String salary; // 연봉
	private String deadline; // 마감일
	private String course; // 요구과정
	private String major; // 요구전공여부

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

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getEmployee_num() {
		return employee_num;
	}

	public void setEmployee_num(String employee_num) {
		this.employee_num = employee_num;
	}

	public String getEstablished_year() {
		return established_year;
	}

	public void setEstablished_year(String established_year) {
		this.established_year = established_year;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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
