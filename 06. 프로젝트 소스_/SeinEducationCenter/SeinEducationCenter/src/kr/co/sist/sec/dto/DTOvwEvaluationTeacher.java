package kr.co.sist.sec.dto;

public class DTOvwEvaluationTeacher {

	private String name; // "과정명",
	private String name2; // "과정명",
	private String name3; // "과정명",
	private String date; // "과정기간",
	private String seq; // "번호",
	private String plan_score; // "강의계획서 이행 점수",
	private String content_score; // "강의내용 이해 점수",
	private String communication_score; // "교사 소통점수",
	private String benefit_score; // "강의 유익성 점수",
	private String satisfaction_score; // "전반적 만족도",
	private String suggestion; // "건의사항"

	public String getName() {
		return name;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getPlan_score() {
		return plan_score;
	}

	public void setPlan_score(String plan_score) {
		this.plan_score = plan_score;
	}

	public String getContent_score() {
		return content_score;
	}

	public void setContent_score(String content_score) {
		this.content_score = content_score;
	}

	public String getCommunication_score() {
		return communication_score;
	}

	public void setCommunication_score(String communication_score) {
		this.communication_score = communication_score;
	}

	public String getBenefit_score() {
		return benefit_score;
	}

	public void setBenefit_score(String benefit_score) {
		this.benefit_score = benefit_score;
	}

	public String getSatisfaction_score() {
		return satisfaction_score;
	}

	public void setSatisfaction_score(String satisfaction_score) {
		this.satisfaction_score = satisfaction_score;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

}
