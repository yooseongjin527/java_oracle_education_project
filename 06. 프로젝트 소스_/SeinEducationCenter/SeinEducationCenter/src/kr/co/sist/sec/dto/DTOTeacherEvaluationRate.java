package kr.co.sist.sec.dto;

public class DTOTeacherEvaluationRate {
	
	private int course_people;
	private int evaluation_people;
	private String evaluation_participation_rate;
	
	public int getCourse_people() {
		return course_people;
	}
	public void setCourse_people(int course_people) {
		this.course_people = course_people;
	}
	public int getEvaluation_people() {
		return evaluation_people;
	}
	public void setEvaluation_people(int evaluation_people) {
		this.evaluation_people = evaluation_people;
	}
	public String getEvaluation_participation_rate() {
		return evaluation_participation_rate;
	}
	public void setEvaluation_participation_rate(String evaluation_participation_rate) {
		this.evaluation_participation_rate = evaluation_participation_rate;
	}
	
	
}
